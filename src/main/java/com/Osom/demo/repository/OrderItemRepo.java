package com.Osom.demo.repository;

import com.Osom.demo.entity.orderEntity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItems , Integer> {
}
