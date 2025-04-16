package com.Osom.demo.controller;

import com.Osom.demo.entity.productEntity.ProductBasicDetails;
import com.Osom.demo.entity.productEntity.ProductImage;
import com.Osom.demo.entity.productEntity.ProductSpecification;
import com.Osom.demo.repository.ProductImgRepo;
import com.Osom.demo.repository.ProductRepo;
import com.Osom.demo.repository.ProductSpecificationRepo;
import com.Osom.demo.serviceImp.ProductImgImp;
import com.Osom.demo.serviceImp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
@Autowired
    ProductImgRepo productImgRepo;
@Autowired
    ProductSpecificationRepo productSpecsRepo;
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

    @GetMapping("/{id}")
    public ProductBasicDetails getSpecificProductDetails(@PathVariable int id) {
        ProductBasicDetails productBasicDetails = productRepo.findById(id).orElse(null);
        return productBasicDetails;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSpecificProduct(@PathVariable int id) {
        productRepo.deleteById(id);
        return "product has been deleted successfully";
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable int id ,
                                @RequestParam (required = false)String name ,
                                @RequestParam (required = false)String description,
                                @RequestParam (required = false)Double price,
                                @RequestParam(required = false) Integer stock_quantity,
                                @RequestParam (required = false)String warehouse_location

    ) {
        ProductBasicDetails productDetails = productRepo.findById(id).orElseThrow(()-> new RuntimeException("object not found"));
        if(name != null){
            productDetails.setName(name);
        }
        if(description != null){
            productDetails.setDescription(description);
        }
        if( price != null){
            productDetails.setPrice(price);
        }
        if(stock_quantity != null){
            productDetails.setStock_quantity(stock_quantity);
        }

        if(warehouse_location != null){
            productDetails.setWarehouse_location(warehouse_location);
        }
        productRepo.save(productDetails);
        return "product has been updated succesfully";
    }


    @PostMapping("/uploadImg")
   public List<String> uploadProductImg( @RequestParam MultipartFile[] img , @RequestParam int product_id){
        List<String> imgUrls = productImg.uploadImg(img , product_id);
        return imgUrls;
   }
   @GetMapping("getImg")
    public List<ProductImage> getProductImg(){
        return  productImgRepo.findAll();
   }



   //add product specification
    @PostMapping("/add-Specification")
    public String addProductSpecification(@RequestParam Double product_size
                                         , @RequestParam String color ,
                                          @RequestParam String material_Used
                                          ,@RequestParam int product_id){
        s.addProductSpecification(product_size , color , material_Used, product_id);
        return "product specification have been added successfully";
    }
    @GetMapping("get-product-specification")
    public List<ProductSpecification> getAllSpecification(){
        return productSpecsRepo.findAll();
    }
    @GetMapping("getSpec/{id}")
    public ProductSpecification getProductSpecification(@PathVariable int id) {
        ProductSpecification productSpecs= productSpecsRepo.findById(id).orElse(null);
        return productSpecs;
    }

    @DeleteMapping("/deleteSpec/{id}")
    public String deleteProductSpecs(@PathVariable int id) {
        productSpecsRepo.deleteById(id);
        return "product specification has been deleted successfully";
    }

    @PutMapping("/updateSpec/{id}")
    public String updateProduct(@PathVariable int id ,
                                @RequestParam (required = false)Double size ,
                                @RequestParam (required = false)String color,
                                @RequestParam (required = false)String material_used

    ) {
        ProductSpecification productSpecs = productSpecsRepo.findById(id).orElseThrow(()-> new RuntimeException("object not found"));
        if(size != null){
            productSpecs.setProduct_size(size);
        }
        if(color != null){
            productSpecs.setColor(color);
        }
        if( material_used != null){
            productSpecs.setMaterial_used(material_used);
        }

        productSpecsRepo.save(productSpecs);
        return "product specification  has been updated succesfully";
    }


}
