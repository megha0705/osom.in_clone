package com.Osom.demo.entity.productEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String img_url;
    @ManyToOne
    @JsonBackReference
    private ProductBasicDetails product_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public ProductBasicDetails getProduct_id() {
        return product_id;
    }

    public void setProduct_id(ProductBasicDetails product_id) {
        this.product_id = product_id;
    }
}
