package com.Osom.demo.controller;

import com.Osom.demo.entity.productEntity.ProductImage;
import com.Osom.demo.repository.ProductImgRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
@RequestMapping("/user")
@RestController
public class UserController {
    //only display the images
    @Autowired
    ProductImgRepo productImgRepo;
    @GetMapping("/show-all")
    public List<String> showProducts(){
        List<String> productImageUrl = productImgRepo.findAll().stream().map(product -> product.getImg_url()).collect(Collectors.toList());
        return productImageUrl;
    }
}
