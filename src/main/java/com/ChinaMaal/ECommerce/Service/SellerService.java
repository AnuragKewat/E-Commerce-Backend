package com.ChinaMaal.ECommerce.Service;

import com.ChinaMaal.ECommerce.Convertor.SellerConvertor;
import com.ChinaMaal.ECommerce.Exception.SellerNotFound;
import com.ChinaMaal.ECommerce.Model.Seller;
import com.ChinaMaal.ECommerce.Repository.SellerRepository;
import com.ChinaMaal.ECommerce.ReqDto.SellerReqDto;
import com.ChinaMaal.ECommerce.ResDto.SellerResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;
    public void addSeller(SellerReqDto sellerReqDto) {
        Seller seller = SellerConvertor.sellerReqDtoToSeller(sellerReqDto);
        sellerRepository.save(seller);
    }
    public List<SellerResDto> getAllSeller() {
        List<Seller> sellers = sellerRepository.findAll();
        List<SellerResDto> sellerResDtos = new ArrayList<>();
        for(Seller seller: sellers) {
            sellerResDtos.add(SellerConvertor.sellerToSellerResDto(seller));
        }
        return sellerResDtos;
    }
    public SellerResDto getSellerByPanNo(String panNo) throws SellerNotFound{
        Seller seller = sellerRepository.findByPanNo(panNo);
        if(seller == null) throw new SellerNotFound("Seller not Exists.");

        SellerResDto sellerResDto = SellerConvertor.sellerToSellerResDto(seller);
        return sellerResDto;
    }
    public List<Seller> getByName(String name) throws SellerNotFound {
        List<Seller> sellers = sellerRepository.findByName(name);
        if(sellers.size() == 0) throw new SellerNotFound("Seller not Exists.");

        return sellers;
    }
}
