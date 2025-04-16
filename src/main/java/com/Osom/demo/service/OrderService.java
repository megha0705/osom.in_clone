package com.Osom.demo.service;

import com.Osom.demo.dto.OrderDetailsDto;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface OrderService {
    public String createOrder(OrderDetailsDto orderDetailsDto) throws StripeException;
}
