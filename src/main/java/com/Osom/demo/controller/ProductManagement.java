package com.Osom.demo.controller;

import com.Osom.demo.entity.productEntity.ProductBasicDetails;
import com.Osom.demo.repository.ProductRepo;
import com.Osom.demo.serviceImp.ProductImgImp;
import com.Osom.demo.serviceImp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("product")
@RestController
public class ProductManagement {
    @Autowired
    ProductImgImp  productImg;
@Autowired
    ProductServiceImp s;
@Autowired
    ProductRepo productRepo;
    //for the admin
    @PostMapping("/addBasicDetails")
    public String addProductBasicDetails(@RequestParam String name,
                           @RequestParam String description,
                           @RequestParam Double price,
                           @RequestParam int stock_quantity,
                           @RequestParam String warehouse_location
    ){
        s.addProductBasicDetails(name , description , price , stock_quantity , warehouse_location);
        return "basic details of the product has been saved successfully";
    }

    //for admin
    @GetMapping("/getAll")
    public List<ProductBasicDetails> getAllProduct(){
       List<ProductBasicDetails>  productBasicDetails = productRepo.findAll();
       return productBasicDetails;
    }
    @PostMapping("/uploadImg")
   public String uploadProductImg(MultipartFile img){
        String imgUrl = productImg.uploadImg(img);
        return imgUrl;
   }
}
