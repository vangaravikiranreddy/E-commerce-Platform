package com.product.product.repositories;

import com.product.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Override
    void deleteById(UUID uuid);

    @Override
    Product save(Product product);
    @Query("SELECT p FROM Product p where p.title = ?1")
    List<Product> getProductsByTitle(String title);
}