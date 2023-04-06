package com.ChinaMaal.ECommerce.Service;

import com.ChinaMaal.ECommerce.Enum.ProductStatus;
import com.ChinaMaal.ECommerce.Model.*;
import com.ChinaMaal.ECommerce.Repository.CustomerRepository;
import com.ChinaMaal.ECommerce.Repository.ProductRepository;
import com.ChinaMaal.ECommerce.ReqDto.OrderReqDto;
import com.ChinaMaal.ECommerce.ResDto.OrderResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderService orderService;

    public String addToCart(OrderReqDto orderReqDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(orderReqDto.getCustomerId()).get();
        }
        catch(Exception e){
            throw new RuntimeException("Invalid Customer id !!!");
        }

        Product product;
        try{
            product = productRepository.findById(orderReqDto.getProductId()).get();
        }
        catch (Exception e){
            throw new RuntimeException("Invalid Product Id");
        }

        if(product.getQuantity()< orderReqDto.getReqQuantity()){
            throw new Exception("Sorry! Required quantity not available");
        }

        Cart cart = customer.getCart();

        int newCost = cart.getCartTotal() + orderReqDto.getReqQuantity()*product.getPrice();
        cart.setCartTotal(newCost);

        // Add item to current cart
        Item item = new Item();
        item.setRequiredQuantity(orderReqDto.getReqQuantity());
        item.setCart(cart);
        item.setProduct(product);
        cart.getItems().add(item);

        customerRepository.save(customer);

        return "Item has been added to your Cart!!";
    }

    public List<OrderResDto> checkout(int customerId) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch(Exception e){
            throw new RuntimeException("Invalid Customer id !!!");
        }

        List<OrderResDto> orderResponseDtos = new ArrayList<>();
        int totalCost = customer.getCart().getCartTotal();
        Cart cart = customer.getCart();
        for(Item item: cart.getItems()){
            Ordered order = new Ordered();
            order.setTotalCost(item.getRequiredQuantity()*item.getProduct().getPrice());
            order.setDeliveryCharge(0);
            order.setCustomer(customer);
            order.getOrderedItems().add(item);

            Card card = customer.getCards().get(0);
            String cardNo = "";
            for(int i=0;i<card.getCardNo().length()-4;i++)
                cardNo += 'X';
            cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
            order.setCardUsedForPayment(cardNo);

            int leftQuantity = item.getProduct().getQuantity()-item.getRequiredQuantity();
            if(leftQuantity<=0)
                item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
            item.getProduct().setQuantity(leftQuantity);

            customer.getOrders().add(order);

            // prepare response dto
            OrderResDto orderResponseDto = OrderResDto.builder()
                    .productName(item.getProduct().getProductName())
                    .orderDate(order.getOrderDate())
                    .quantityOrdered(item.getRequiredQuantity())
                    .cardUsedForPayment(cardNo)
                    .itemPrice(item.getProduct().getPrice())
                    .totalCost(order.getTotalCost())
                    .deliveryCharge(0)
                    .build();

            orderResponseDtos.add(orderResponseDto);
        }

        cart.setItems(new ArrayList<>());
        cart.setCartTotal(0);
        customerRepository.save(customer);

        return orderResponseDtos;
    }
}
