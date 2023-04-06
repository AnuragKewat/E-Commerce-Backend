package com.ChinaMaal.ECommerce.Convertor;

import com.ChinaMaal.ECommerce.Model.Seller;
import com.ChinaMaal.ECommerce.ReqDto.SellerReqDto;
import com.ChinaMaal.ECommerce.ResDto.SellerResDto;
import lombok.Builder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerConvertor {
    public static Seller sellerReqDtoToSeller(SellerReqDto sellerReqDto) {
        return Seller.builder()
                .name(sellerReqDto.getName())
                .email(sellerReqDto.getEmail())
                .mobNo(sellerReqDto.getMobNo())
                .panNo(sellerReqDto.getPanNo())
                .build();
    }
    public static SellerResDto sellerToSellerResDto(Seller seller) {
        return SellerResDto.builder()
                .name(seller.getName())
                .email(seller.getEmail())
                .mobNo(seller.getMobNo())
                .panNo(seller.getPanNo())
                .build();
    }
}
