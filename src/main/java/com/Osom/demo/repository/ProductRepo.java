package com.Osom.demo.repository;

import com.Osom.demo.entity.productEntity.ProductBasicDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ProductRepo extends JpaRepository<ProductBasicDetails , Integer> {
}
