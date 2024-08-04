package com.cb.repository;

import com.cb.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.delete=false ")
    List<Product> findAll(Sort sort);
    Optional<Product> findById(Integer id);

//    List<Product> findByDeleteFalse(Sort sort);
}
