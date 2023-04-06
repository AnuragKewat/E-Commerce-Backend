package com.ChinaMaal.ECommerce.ReqDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerReqDto {
    String name;
    String email;
    String mobNo;
    String panNo;
}
