package com.Osom.demo.entity.productEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String category_name;
    @ManyToOne
    private ProductBasicDetails product_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public ProductBasicDetails getProduct_id() {
        return product_id;
    }

    public void setProduct_id(ProductBasicDetails product_id) {
        this.product_id = product_id;
    }
}
