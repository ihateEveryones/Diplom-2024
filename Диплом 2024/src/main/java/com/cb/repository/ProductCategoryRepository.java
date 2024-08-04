package com.cb.repository;

import com.cb.model.CategoryServices;
import com.cb.model.ProductCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Integer> {
    @Query("SELECT p FROM ProductCategory p WHERE p.delete=false ")
    List<ProductCategory> findAll();
//    @Query("SELECT cp.id_category.id_category, cp.id_category.name_category, cp.id_product.id_product, cp.id_product.name_product " +
//            "FROM ProductCategory cp " +
//            "WHERE cp.delete = false")
//    List<Object[]> getCategoryProduct();

    @Query("SELECT p.id_product, p.name_product,p.price,p.description,p.productImage, COUNT(*) AS totalOrders " +
            "FROM OrderProductsInfo opi " +
            "JOIN ProductCategory pc ON opi.id_product_category.id_product_category = pc.id_product_category " +
            "JOIN Product p ON pc.id_product.id_product = p.id_product " +
            "GROUP BY p.id_product, p.name_product " +
            "ORDER BY totalOrders DESC " +
            "LIMIT 4")
    List<Object[]> findTop4PopularCategories();
    @Query("SELECT cs " +

            "FROM ProductCategory cs " +
            "WHERE cs.delete = false")
    List<ProductCategory> getCategoryProduct();
//    @Query("SELECT cs.id_category.id_category, cs.id_category.name_category, " +
//            "cs.id_service.id_service, cs.id_service.name_service " +
//            "FROM CategoryServices cs " +
//            "WHERE cs.delete = false")

}
