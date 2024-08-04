package com.cb.repository;

import com.cb.model.Category;
import com.cb.model.CategoryProd;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryReposittory extends CrudRepository<Category, Integer> {
    @Query("SELECT p FROM Category p WHERE p.delete=false ")
    List<Category> findAll();
    Optional<Category> findById(Integer id);

    @Query("SELECT s FROM Category s WHERE s.delete=false ")
    List<Category> findAllSorted(Sort sort);
}
