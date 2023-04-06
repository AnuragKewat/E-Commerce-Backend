package com.ChinaMaal.ECommerce.Controller;

import com.ChinaMaal.ECommerce.ReqDto.OrderReqDto;
import com.ChinaMaal.ECommerce.ResDto.OrderResDto;
import com.ChinaMaal.ECommerce.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderReqDto orderReqDto) {
        OrderResDto orderResDto;
        try{
            orderResDto = orderService.placeOrder(orderReqDto);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(orderResDto, HttpStatus.ACCEPTED);
    }
}
