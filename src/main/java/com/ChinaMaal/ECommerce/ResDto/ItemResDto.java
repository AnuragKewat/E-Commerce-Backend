package com.ChinaMaal.ECommerce.ResDto;

import com.ChinaMaal.ECommerce.Enum.ProductCategory;
import com.ChinaMaal.ECommerce.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResDto {
    private String productName;

    private int price;

    private ProductCategory productCategory;

    private ProductStatus productStatus;
}
