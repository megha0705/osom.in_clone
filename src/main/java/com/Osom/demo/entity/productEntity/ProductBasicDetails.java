package com.Osom.demo.entity.productEntity;

import com.Osom.demo.entity.orderEntity.OrderItems;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.data.annotation.LastModifiedDate;
import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ProductBasicDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
@Column(columnDefinition = "TEXT")
private String description;
private double price;
private int stock_quantity;
private String warehouse_location;
private ProductStatus status_availability;
@CreationTimestamp
private LocalDateTime created_at;
@LastModifiedDate
private LocalDateTime updated_at;

@OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL)
private List<ProductCategory> product_category;
    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProductImage> product_img;
    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL)
    private List<ProductSpecification> product_specification;
@OneToMany(mappedBy =  "product_id" , cascade = CascadeType.ALL)
private List<OrderItems> orderItems;
    public void updateAvailability(){
        if(stock_quantity > 0) {
            this.status_availability = ProductStatus.IN_STOCK;
        }else if(stock_quantity == 0){
            this.status_availability = ProductStatus.OUT_OF_STOCK;

        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }


    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
        updateAvailability();
    }

    public String getWarehouse_location() {
        return warehouse_location;
    }

    public void setWarehouse_location(String warehouse_location) {
        this.warehouse_location = warehouse_location;
    }

    public ProductStatus getStatus_availability() {
        return status_availability;
    }

    public void setStatus_availability(ProductStatus status_availability) {
        this.status_availability = status_availability;
    }
}
