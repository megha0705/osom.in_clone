package com.Osom.demo.service;

import org.springframework.web.bind.annotation.RequestParam;

public interface ProductService {
    public void addProductBasicDetails( String name,
                                        String description,
                                        Double price,
                                        int stock_quantity,
                                        String warehouse_location);
    public void addProductSpecification(Double product_size , String color , String material_used, int product_id);
}
