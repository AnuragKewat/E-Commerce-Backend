package com.ChinaMaal.ECommerce.Convertor;

import com.ChinaMaal.ECommerce.Model.Product;
import com.ChinaMaal.ECommerce.ReqDto.ProductReqDto;
import com.ChinaMaal.ECommerce.ResDto.ProductResDto;

public class ProductConvertor {
    public static Product productReqToProduct(ProductReqDto productReqDto) {
        return Product.builder()
                .productName(productReqDto.getProductName())
                .price(productReqDto.getPrice())
                .quantity(productReqDto.getQuantity())
                .productCategory(productReqDto.getProductCategory())
                .build();
    }

    public  static ProductResDto productToProductResDto(Product product) {
        return ProductResDto.builder()
                .name(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
