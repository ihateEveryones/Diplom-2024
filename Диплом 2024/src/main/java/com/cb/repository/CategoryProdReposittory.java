package com.cb.repository;

import com.cb.model.Category;
import com.cb.model.CategoryProd;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryProdReposittory extends JpaRepository<CategoryProd, Integer> {
    @Query("SELECT p FROM CategoryProd p WHERE p.delete=false ")
    List<CategoryProd> findAll();

    @Query("SELECT p FROM CategoryProd p WHERE p.delete=false ")
    List<CategoryProd> findAllSorted(Sort sort);
}
