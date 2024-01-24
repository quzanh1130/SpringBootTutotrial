package com.tutorial.apidemo.SpringBootTutorial.repositories;


import com.tutorial.apidemo.SpringBootTutorial.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByProductName(String productName);
}
