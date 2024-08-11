package com.example.capstone_1.Service;


import com.example.capstone_1.Controller.CategoryController;
import com.example.capstone_1.Model.Product;
import com.example.capstone_1.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final CategoryService categoryService;
    private final ProductRepository productRepository;



    public ArrayList<Product> getProducts() {
        return new ArrayList<Product>(productRepository.findAll());
    }

    public String addProduct(Product product) {
        if (categoryService.existsById(product.getCategoryId())) {
            productRepository.save(product);
            return "true";
        }
        return "Category does not exist";
    }

    public String updateProduct(int id,Product product) {
        Product p = productRepository.getById(id);
        if (p != null) {
            if (categoryService.existsById(product.getCategoryId())) {
                p.setName(product.getName());
                p.setCategoryId(product.getCategoryId());
                p.setPrice(product.getPrice());
                productRepository.save(p);
                return "true";
            }
            return "Category does not exist";
        }
        return "Product does not exist";
    }

    public boolean deleteProduct(int id) {
        if (categoryService.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsById(int id) {
        return productRepository.existsById(id);
    }







}
