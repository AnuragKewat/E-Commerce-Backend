package com.ChinaMaal.ECommerce.ReqDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderReqDto {
    private int productId;
    private int customerId;
    private int reqQuantity;
}
