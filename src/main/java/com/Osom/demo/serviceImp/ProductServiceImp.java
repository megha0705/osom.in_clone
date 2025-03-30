package com.Osom.demo.serviceImp;

import com.Osom.demo.entity.productEntity.ProductBasicDetails;
import com.Osom.demo.repository.ProductRepo;
import com.Osom.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp  implements ProductService {
    @Autowired
    ProductRepo productRepo;
    @Override
    public void addProductBasicDetails(String name, String description, Double price, int stock_quantity, String warehouse_location) {
        ProductBasicDetails productBasicDetails = new ProductBasicDetails();
        productBasicDetails.setDescription(description);
        productBasicDetails.setName(name);
        productBasicDetails.setPrice(price);
        productBasicDetails.setStock_quantity(stock_quantity);
        productBasicDetails.setWarehouse_location(warehouse_location);
        productRepo.save(productBasicDetails);
    }
}
