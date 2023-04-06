package com.ChinaMaal.ECommerce.Controller;

import com.ChinaMaal.ECommerce.ReqDto.OrderReqDto;
import com.ChinaMaal.ECommerce.ResDto.OrderResDto;
import com.ChinaMaal.ECommerce.Service.CartService;
import com.ChinaMaal.ECommerce.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @PostMapping("/add")
    public String addToCart(@RequestBody OrderReqDto orderReqDto) {
        String response = "";
        try{
            response = cartService.addToCart(orderReqDto);
        } catch (Exception e) {
            return e.getMessage();
        }

        return response;
    }
    @PostMapping("/checkout/{customerId}")
    public ResponseEntity checkout(@PathVariable("customerId") int customerId) {
        List<OrderResDto> orderResponseDtos;
        try{
            orderResponseDtos = cartService.checkout(customerId);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(orderResponseDtos,HttpStatus.ACCEPTED);
    }
}
