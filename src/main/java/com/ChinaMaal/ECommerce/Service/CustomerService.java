package com.ChinaMaal.ECommerce.Service;

import com.ChinaMaal.ECommerce.Convertor.CustomerConvertor;
import com.ChinaMaal.ECommerce.Model.Customer;
import com.ChinaMaal.ECommerce.Model.Cart;
import com.ChinaMaal.ECommerce.Repository.CustomerRepository;
import com.ChinaMaal.ECommerce.Repository.ProductRepository;
import com.ChinaMaal.ECommerce.ReqDto.CustomerReqDto;
import com.ChinaMaal.ECommerce.ResDto.CustomerResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResDto addCustomer(CustomerReqDto customerReqDto) {
        Customer customer = CustomerConvertor.customerReqDtoToCustomer(customerReqDto);

        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        customer.setCart(cart);

        customerRepository.save(customer);

        return CustomerConvertor.customerToCustomerResDto(customer);
    }
    public CustomerResDto getById(int customerId) {
        Customer customer =  customerRepository.findById(customerId).get();
        if(customer == null) throw new RuntimeException("Customer Not Found.");

        return CustomerConvertor.customerToCustomerResDto(customer);
    }
    public List<CustomerResDto> findAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResDto> customerResDtos = new ArrayList<>();
        for(Customer customer: customers) {
            customerResDtos.add(CustomerConvertor.customerToCustomerResDto(customer));
        }
        return customerResDtos;
    }
}
