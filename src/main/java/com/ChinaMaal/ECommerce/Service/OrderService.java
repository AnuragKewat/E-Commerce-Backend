package com.ChinaMaal.ECommerce.Service;

import com.ChinaMaal.ECommerce.Enum.ProductStatus;
import com.ChinaMaal.ECommerce.Model.Card;
import com.ChinaMaal.ECommerce.Model.Item;
import com.ChinaMaal.ECommerce.Model.Customer;
import com.ChinaMaal.ECommerce.Model.Ordered;
import com.ChinaMaal.ECommerce.Model.Product;
import com.ChinaMaal.ECommerce.Repository.CustomerRepository;
import com.ChinaMaal.ECommerce.Repository.OrderRepository;
import com.ChinaMaal.ECommerce.Repository.ProductRepository;
import com.ChinaMaal.ECommerce.ReqDto.OrderReqDto;
import com.ChinaMaal.ECommerce.ResDto.CustomerResDto;
import com.ChinaMaal.ECommerce.ResDto.OrderResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    public OrderResDto placeOrder(OrderReqDto orderReqDto) {
        Customer customer = customerRepository.findById(orderReqDto.getCustomerId()).get();
        if(customer == null) throw new RuntimeException("Customer Not Exists.");

        Product product = productRepository.findById(orderReqDto.getProductId()).get();
        if(product == null) throw new RuntimeException("Product Not Exists.");

        if(product.getQuantity()<orderReqDto.getReqQuantity())
            throw new RuntimeException("Required Quantity Not Available.");

        Ordered order = new Ordered();
        order.setTotalCost(orderReqDto.getReqQuantity() * product.getPrice());
        order.setDeliveryCharge(40);

        Card card = customer.getCards().get(0);
        String cardNo = "";
        for(int i=0;i<card.getCardNo().length()-4;i++)
            cardNo += 'X';
        cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
        order.setCardUsedForPayment(cardNo);

        Item item = new Item();
        item.setRequiredQuantity(orderReqDto.getReqQuantity());
        item.setProduct(product);
        item.setOrder(order);
        order.getOrderedItems().add(item);
        order.setCustomer(customer);

        int leftQuantity = product.getQuantity()-orderReqDto.getReqQuantity();
        if(leftQuantity<=0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        product.setQuantity(leftQuantity);

        customer.getOrders().add(order);
        Customer savedCustomer = customerRepository.save(customer);
        Ordered savedOrder = savedCustomer.getOrders().get(savedCustomer.getOrders().size()-1);

        OrderResDto orderResponseDto = OrderResDto.builder()
                .productName(product.getProductName())
                .orderDate(savedOrder.getOrderDate())
                .quantityOrdered(orderReqDto.getReqQuantity())
                .cardUsedForPayment(cardNo)
                .itemPrice(product.getPrice())
                .totalCost(order.getTotalCost())
                .deliveryCharge(40)
                .build();

        return orderResponseDto;
    }
}
