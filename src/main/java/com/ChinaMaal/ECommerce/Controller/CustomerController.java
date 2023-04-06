package com.ChinaMaal.ECommerce.Controller;

import com.ChinaMaal.ECommerce.ReqDto.CustomerReqDto;
import com.ChinaMaal.ECommerce.ResDto.CustomerResDto;
import com.ChinaMaal.ECommerce.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerReqDto customerReqDto) {
        CustomerResDto customerResDto = customerService.addCustomer(customerReqDto);

        return new ResponseEntity(customerResDto, HttpStatus.ACCEPTED);
    }
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getCustomerById(@PathVariable("id") int customerId) {
        CustomerResDto customerResDto;
        try{
            customerResDto = customerService.getById(customerId);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customerResDto, HttpStatus.ACCEPTED);
    }
    @GetMapping("/get-all-customer")
    public ResponseEntity viewAll() {
        List<CustomerResDto> customerResDtoList = customerService.findAllCustomer();
        return new ResponseEntity(customerResDtoList, HttpStatus.ACCEPTED);
    }
}
