package com.ChinaMaal.ECommerce.Service;

import com.ChinaMaal.ECommerce.Model.Card;
import com.ChinaMaal.ECommerce.Model.Customer;
import com.ChinaMaal.ECommerce.Repository.CardRepository;
import com.ChinaMaal.ECommerce.Repository.CustomerRepository;
import com.ChinaMaal.ECommerce.ReqDto.CardReqDto;
import com.ChinaMaal.ECommerce.ResDto.CardDto;
import com.ChinaMaal.ECommerce.ResDto.CardResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CustomerRepository customerRepository;

    public CardResDto addCard(CardReqDto cardReqDto) throws Exception {

        Customer customer;

        try{
            customer = customerRepository.findById(cardReqDto.getCustomerId()).get();
        }
        catch(Exception e){
            throw new RuntimeException("Invalid customer id");
        }

        // Make a card object
        Card card = Card.builder()
                .cardNo(cardReqDto.getCardNo())
                .cvv(cardReqDto.getCvv())
                .cardType(cardReqDto.getCardType())
                .customer(customer)
                .build();

        // add the card to current card list of customer
        customer.getCards().add(card);

        customerRepository.save(customer); // save both customer and card

        // prepare Response Dto
        CardResDto cardResDto = new CardResDto();
        cardResDto.setName(customer.getName());

        // convert every card to cardDto
        List<CardDto> cardDtoList = new ArrayList<>();
        for(Card card1: customer.getCards()){
            CardDto cardDto = new CardDto();
            cardDto.setCardNo(card1.getCardNo());
            cardDto.setCardType(card1.getCardType());

            cardDtoList.add(cardDto);
        }

        cardResDto.setCardDtos(cardDtoList);
        return cardResDto;
    }
}
