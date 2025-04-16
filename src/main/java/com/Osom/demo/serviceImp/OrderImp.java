package com.Osom.demo.serviceImp;

import com.Osom.demo.config.StripeConfig;
import com.Osom.demo.dto.OrderDetailsDto;
import com.Osom.demo.dto.OrderItemsDto;
import com.Osom.demo.entity.orderEntity.OrderDetails;
import com.Osom.demo.entity.orderEntity.OrderItems;
import com.Osom.demo.entity.orderEntity.OrderStatus;
import com.Osom.demo.entity.productEntity.ProductBasicDetails;
import com.Osom.demo.repository.OrderDetailsRepo;
import com.Osom.demo.repository.OrderItemRepo;
import com.Osom.demo.repository.ProductRepo;
import com.Osom.demo.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderImp implements OrderService {
    @Autowired
    StripeConfig stripeConfig;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    OrderItemRepo orderItemRepo;
    @Autowired
    OrderDetailsRepo orderDetailsRepo;
    public OrderImp(StripeConfig stripeConfig){
        this.stripeConfig = stripeConfig;
    }
    @Override
    public String createOrder(OrderDetailsDto orderDetailsDto) throws StripeException {
        PaymentIntent paymentIntent = stripeConfig.createPaymentIntent(orderDetailsDto.getTotalAmount(), "inr");


            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setAmount(orderDetailsDto.getTotalAmount());
            orderDetails.setStatus(OrderStatus.PENDING);
            orderDetails.setPayment_intent(paymentIntent.getId());
            orderDetailsRepo.save(orderDetails);
            List<OrderItems> orderItems = new ArrayList<>();
            for (OrderItemsDto order : orderDetailsDto.getOrderItems()) {
                ProductBasicDetails product = productRepo.findById(order.getProductId()).orElseThrow(() -> new RuntimeException("product not found"));
                OrderItems orderItem = new OrderItems();
                orderItem.setProduct_id(product);
                orderItem.setQuantity(order.getQuantity());
                orderItem.setOrder_details_id(orderDetails);
                orderItems.add(orderItem);
            }
            orderItemRepo.saveAll(orderItems);
            orderDetails.setItems(orderItems);
            orderDetailsRepo.save(orderDetails);


        return paymentIntent.toJson();
    }
}
