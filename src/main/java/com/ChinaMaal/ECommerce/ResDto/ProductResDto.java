package com.ChinaMaal.ECommerce.ResDto;

import com.ChinaMaal.ECommerce.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResDto {
    private String name;
    private int price;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
}
