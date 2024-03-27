package com.product.product.repositories;

import com.product.product.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface SearchRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
    List<Product> findAllByTitleContaining(String title, Pageable pageable);
}
