package com.ChinaMaal.ECommerce.Controller;

import com.ChinaMaal.ECommerce.Enum.ProductCategory;
import com.ChinaMaal.ECommerce.Model.Product;
import com.ChinaMaal.ECommerce.ReqDto.ProductReqDto;
import com.ChinaMaal.ECommerce.ResDto.ProductResDto;
import com.ChinaMaal.ECommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductReqDto productReqDto) {
        ProductResDto productResDto;
        try {
            productResDto = productService.addProduct(productReqDto);
        }
        catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(productResDto, HttpStatus.CREATED);
    }
    @GetMapping("/get-by-category/{category}")
    public ResponseEntity getAllByCategory(@PathVariable("category") ProductCategory productCategory) {
        List<ProductResDto> productResDtoList = productService.getAllByCategory(productCategory);

        return new ResponseEntity(productResDtoList, HttpStatus.ACCEPTED);
    }
}
