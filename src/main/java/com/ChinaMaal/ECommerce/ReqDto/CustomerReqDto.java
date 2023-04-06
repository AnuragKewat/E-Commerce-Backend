package com.ChinaMaal.ECommerce.ReqDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReqDto {
    private String name;

    private int age;

    private String email;

    private String mobNo;
}
