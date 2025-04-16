package com.Osom.demo.repository;

import com.Osom.demo.entity.productEntity.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpecificationRepo extends JpaRepository< ProductSpecification, Integer > {
}
