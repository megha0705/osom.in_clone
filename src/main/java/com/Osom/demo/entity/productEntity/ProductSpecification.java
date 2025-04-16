package com.Osom.demo.entity.productEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ProductSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double product_size;
    private String color;
    private String material_used;
    @ManyToOne
    private ProductBasicDetails product_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getProduct_size() {
        return product_size;
    }

    public void setProduct_size(double product_size) {
        this.product_size = product_size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial_used() {
        return material_used;
    }

    public void setMaterial_used(String material_used) {
        this.material_used = material_used;
    }

    public ProductBasicDetails getProduct_id() {
        return product_id;
    }

    public void setProduct_id(ProductBasicDetails product_id) {
        this.product_id = product_id;
    }
}
