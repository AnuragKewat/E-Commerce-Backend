package com.ChinaMaal.ECommerce.ResDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerResDto {
    String name;
    String mobNo;
    String email;
    String panNo;
}
