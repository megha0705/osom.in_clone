package com.Osom.demo.repository;

import com.Osom.demo.entity.productEntity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImgRepo extends JpaRepository<ProductImage , Integer> {
}
