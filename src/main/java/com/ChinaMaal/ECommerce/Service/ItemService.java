package com.ChinaMaal.ECommerce.Service;

import com.ChinaMaal.ECommerce.Model.Item;
import com.ChinaMaal.ECommerce.Model.Product;
import com.ChinaMaal.ECommerce.Repository.ItemRepository;
import com.ChinaMaal.ECommerce.Repository.ProductRepository;
import com.ChinaMaal.ECommerce.ResDto.ItemResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ProductRepository productRepository;
    public ItemResDto viewItem(int productId) {
        Product product = productRepository.findById(productId).get();
        if(product == null) throw new RuntimeException("Product Not Found.");

        Item item = Item.builder()
                .product(product)
                .requiredQuantity(0)
                .build();

        product.setItem(item);
        productRepository.save(product);

        ItemResDto itemResponseDto = ItemResDto.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .productStatus(product.getProductStatus())
                .build();

        return itemResponseDto;
    }
}
