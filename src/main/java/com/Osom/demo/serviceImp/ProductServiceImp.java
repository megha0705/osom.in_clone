package com.Osom.demo.serviceImp;

import com.Osom.demo.entity.productEntity.ProductBasicDetails;
import com.Osom.demo.entity.productEntity.ProductSpecification;
import com.Osom.demo.repository.ProductRepo;
import com.Osom.demo.repository.ProductSpecificationRepo;
import com.Osom.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp  implements ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ProductSpecificationRepo productSpecsRepo;
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

    @Override
    public void addProductSpecification(Double product_size, String color, String material_used , int product_id) {
        ProductSpecification productSpecification = new ProductSpecification();
        ProductBasicDetails products = productRepo.findById(product_id).orElseThrow(()->  new RuntimeException("product with this" + product_id + "is not found"));

        productSpecification.setProduct_size(product_size);
        productSpecification.setColor(color);
        productSpecification.setMaterial_used(material_used);
        productSpecification.setProduct_id(products);
        productSpecsRepo.save(productSpecification);
    }
}
