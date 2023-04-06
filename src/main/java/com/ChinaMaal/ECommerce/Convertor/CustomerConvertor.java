package com.ChinaMaal.ECommerce.Convertor;

import com.ChinaMaal.ECommerce.Model.Customer;
import com.ChinaMaal.ECommerce.ReqDto.CustomerReqDto;
import com.ChinaMaal.ECommerce.ResDto.CustomerResDto;

public class CustomerConvertor {
    public static Customer customerReqDtoToCustomer(CustomerReqDto customerReqDto) {
        return Customer.builder()
                .name(customerReqDto.getName())
                .age(customerReqDto.getAge())
                .mobNo(customerReqDto.getMobNo())
                .email(customerReqDto.getEmail())
                .build();
    }
    public static CustomerResDto customerToCustomerResDto(Customer customer) {
        return CustomerResDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
    }
}
