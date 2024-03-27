package com.product.product;


import com.product.product.models.Category;
import com.product.product.models.Price;
import com.product.product.models.Product;
import com.product.product.repositories.CategoryRepository;
import com.product.product.repositories.PriceRepository;
import com.product.product.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductApplication  {

//    private ProductRepository productRepository;
//    private CategoryRepository categoryRepository;
////    private PriceRepository priceRepository;
//
//    public ProductApplication(ProductRepository productRepository, CategoryRepository categoryRepository, PriceRepository priceRepository) {
////        this.productRepository = productRepository;
//        this.categoryRepository = categoryRepository;
////        this.priceRepository = priceRepository;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

//    @Override
//    public void run(String... args)  {
//
////            Price price = new Price();
////            price.setCurrency("INR");
////            price.setValue(125000);
////            Price price1 = priceRepository.save(price);
////
////            Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString("09560a9d-1428-450c-9726-da9312d58104"));
////            Category category = optionalCategory.get();
////
////
////          Product product = new Product();
////          product.setTitle("iphone 15");
////          product.setCategory(category);
////          product.setPrice(price1);
////          Product product1 = productRepository.save(product);
//
//    }
}
