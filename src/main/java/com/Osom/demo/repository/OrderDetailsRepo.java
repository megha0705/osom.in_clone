package com.Osom.demo.repository;

import com.Osom.demo.entity.orderEntity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails , Integer> {
}
