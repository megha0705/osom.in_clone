package com.Osom.demo.serviceImp;

import com.Osom.demo.entity.productEntity.ProductBasicDetails;
import com.Osom.demo.entity.productEntity.ProductImage;
import com.Osom.demo.repository.ProductImgRepo;
import com.Osom.demo.repository.ProductRepo;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class ProductImgImp {
    @Autowired
    Cloudinary cloudinary;
    @Autowired
    ProductImgRepo productImgRepo;
    @Autowired
    ProductRepo productRepo;
    public List<String> uploadImg(MultipartFile[] images, int product_id){
        List<String> imageUrls = new ArrayList<>();
        for(MultipartFile image : images) {
            try {
                Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
                String imgUrl = uploadResult.get("url").toString();
                imageUrls.add(imgUrl);
                ProductImage productImage = new ProductImage();
                ProductBasicDetails products = productRepo.findById(product_id).orElseThrow(()->  new RuntimeException("product with this" + product_id + "is not found"));
                productImage.setImg_url(imgUrl);
                productImage.setProduct_id(products);
                productImgRepo.save(productImage);
            }catch (IOException e) {
                throw new RuntimeException("Image upload failed", e);
            }
        }
        return imageUrls;
    }
}
