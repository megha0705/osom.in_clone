package com.Osom.demo.dto;
import java.util.*;
public class OrderDetailsDto {
 private List<OrderItemsDto> orderItems;
    private double totalAmount;

    public List<OrderItemsDto> getOrderItems() {
        return orderItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
