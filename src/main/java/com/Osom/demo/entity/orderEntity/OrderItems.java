package com.Osom.demo.entity.orderEntity;

import com.Osom.demo.entity.productEntity.ProductBasicDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private ProductBasicDetails product_id;

    private int quantity;

    @ManyToOne
    private OrderDetails order_details_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductBasicDetails getProduct_id() {
        return product_id;
    }

    public void setProduct_id(ProductBasicDetails product_id) {
        this.product_id = product_id;
    }

    public OrderDetails getOrder_details_id() {
        return order_details_id;
    }

    public void setOrder_details_id(OrderDetails order_details_id) {
        this.order_details_id = order_details_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



}
