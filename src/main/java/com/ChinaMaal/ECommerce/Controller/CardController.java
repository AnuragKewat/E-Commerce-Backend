package com.ChinaMaal.ECommerce.Controller;

import com.ChinaMaal.ECommerce.ReqDto.CardReqDto;
import com.ChinaMaal.ECommerce.ResDto.CardResDto;
import com.ChinaMaal.ECommerce.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;
    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardReqDto cardReqDto) {
        CardResDto cardResponseDto;
        try{
            cardResponseDto = cardService.addCard(cardReqDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(cardResponseDto,HttpStatus.ACCEPTED);
    }
}
