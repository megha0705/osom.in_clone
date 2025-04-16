package com.Osom.demo.controller;

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
import com.Osom.demo.serviceImp.OrderImp;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/payment")
@RestController
public class PaymentController {
   /* @Autowired
    StripeConfig stripeConfig;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    OrderItemRepo orderItemRepo;
    @Autowired
    OrderDetailsRepo orderDetailsRepo;
    public PaymentController(StripeConfig stripeConfig){
        this.stripeConfig = stripeConfig;
    }
    @PostMapping("/createIntent")
    public String createPaymentIntent(@RequestBody OrderDetailsDto orderDetailsDto)throws StripeException {
        PaymentIntent paymentIntent = stripeConfig.createPaymentIntent(orderDetailsDto.getTotalAmount() , "inr");
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setAmount(orderDetailsDto.getTotalAmount());
        orderDetails.setStatus(OrderStatus.PENDING);
        orderDetails.setPayment_intent(paymentIntent.getId());

        List<OrderItems> orderItems = new ArrayList<>();
        for(OrderItemsDto order: orderDetailsDto.getOrderItems()){
            ProductBasicDetails product = productRepo.findById(order.getProductId()).orElseThrow(()-> new RuntimeException("product not found"));
            OrderItems orderItem = new OrderItems();
            orderItem.setProduct_id(product);
            orderItem.setQuantity(order.getQuantity());
            orderItems.add(orderItem);
        }
        orderItemRepo.saveAll(orderItems);
        orderDetails.setItems(orderItems);
        orderDetailsRepo.save(orderDetails);
        return paymentIntent.toJson();
        //in the frontend use the client secret to take card details from the user
    }*/
    @Autowired
    OrderImp orderImp;
    @PostMapping("/createIntent")
    public String createPaymentIntent(@RequestBody OrderDetailsDto orderDetailsDto) throws StripeException{
        return orderImp.createOrder(orderDetailsDto);

    }
    @Value("${stripe.webhook.secret}")
    private String endPointSecret;
    @PostMapping("/webhook")
    public void stripeWebhookHandeling(@RequestBody String payload , @RequestHeader ("Stripe-Signature") String sigHeader){
        Event event =  null;
        try{
            event = Webhook.constructEvent(payload , sigHeader , endPointSecret);
        }catch (SignatureVerificationException e){
            System.out.println("error found "+ e.getMessage());
        }
        if (event != null && "payment_intent.succeeded".equals(event.getType())) {
            PaymentIntent paymentIntent = (PaymentIntent) event.getDataObjectDeserializer()
                    .getObject()
                    .orElse(null);

            System.out.println("Payment succeeded: " + paymentIntent.getId());
            // Your business logic here
        } else if(event != null) {
            System.out.println("Unhandled event type: " + event.getType());
        }
    }
}
