package com.cb.repository;

import com.cb.model.CategoryServices;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryServiceRepository extends CrudRepository<CategoryServices, Integer > {
    @Query("SELECT p FROM CategoryServices p WHERE p.delete=false ")
    List<CategoryServices> findAll();
//    @Query("SELECT cs.id_category.id_category, cs.id_category.name_category, " +
//            "cs.id_service.id_service, cs.id_service.name_service " +
//            "FROM CategoryServices cs " +
//            "WHERE cs.delete = false")
//    List<Object[]> getCategoryServices();

    @Query("SELECT cs " +

          "FROM CategoryServices cs " +
           "WHERE cs.delete = false")
    List<CategoryServices> getCategoryServices();

    @Query("SELECT cs.id_category.id_category,cs.id_category.name_category, MIN(s.price) AS minPrice " +
            "FROM CategoryServices cs  " +
            "JOIN cs.id_category c " +
            "JOIN cs.id_service s " +
            "WHERE cs.delete = false and c.delete=false " +
            "GROUP BY c.id_category")
    List<Object[]> getMinPricesByCategory();
    Optional<CategoryServices> findById(Integer id);
}
