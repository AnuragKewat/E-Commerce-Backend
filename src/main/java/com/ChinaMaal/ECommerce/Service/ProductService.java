package com.ChinaMaal.ECommerce.Service;

import com.ChinaMaal.ECommerce.Convertor.ProductConvertor;
import com.ChinaMaal.ECommerce.Enum.ProductCategory;
import com.ChinaMaal.ECommerce.Enum.ProductStatus;
import com.ChinaMaal.ECommerce.Exception.SellerNotFound;
import com.ChinaMaal.ECommerce.Model.Product;
import com.ChinaMaal.ECommerce.Model.Seller;
import com.ChinaMaal.ECommerce.Repository.ProductRepository;
import com.ChinaMaal.ECommerce.Repository.SellerRepository;
import com.ChinaMaal.ECommerce.ReqDto.ProductReqDto;
import com.ChinaMaal.ECommerce.ResDto.ProductResDto;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;
    public ProductResDto addProduct(ProductReqDto productReqDto) throws SellerNotFound {
        Product product = ProductConvertor.productReqToProduct(productReqDto);
        product.setProductStatus(ProductStatus.AVAILABLE);

        Seller seller = sellerRepository.findById(productReqDto.getSellerId()).get();
        if(seller == null) throw new SellerNotFound("Seller Not Exists.");

        product.setSeller(seller);
        List<Product> productList = seller.getProducts();
        productList.add(product);
        seller.setProducts(productList);

        sellerRepository.save(seller);

        ProductResDto productResDto = ProductConvertor.productToProductResDto(product);
        return productResDto;
    }
    public List<ProductResDto> getAllByCategory(ProductCategory productCategory) {
        List<Product> products = productRepository.findAllByProductCategory(productCategory);

        List<ProductResDto> productResDtos = new ArrayList<>();
        for(Product product: products) {
            productResDtos.add(ProductConvertor.productToProductResDto(product));
        }

        return productResDtos;
    }
}
