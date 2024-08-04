package com.cb.controller;

import com.cb.model.*;
import com.cb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NewProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryProdReposittory categoryProdReposittory;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @PostMapping("/createProduct")
    public String createService(@RequestParam String name_product, @RequestParam Double price,@RequestParam String description) {
        Product product= new Product();
        product.setName_product(name_product);
        product.setPrice(price);
        product.setDescription(description);
        productRepository.save(product);
        return "redirect:/createNewProduct";
    }
    @PostMapping("/createCategoryProd")
    public String createCategory(@RequestParam String name_category) {
        CategoryProd categoryProd= new CategoryProd();
        categoryProd.setName_category(name_category);

        categoryProdReposittory.save(categoryProd);
        return "redirect:/createNewProduct";
    }
    @PostMapping("/createProductCategory")
    public String createCategoryService(@RequestParam Integer id_product,@RequestParam Integer id_category_product ) {
        ProductCategory productCategory= new ProductCategory();
        Product product = new Product();
        product.setId_product(id_product);

        CategoryProd categoryProd = new CategoryProd();
        categoryProd.setId_category(id_category_product);
        productCategory.setId_category(categoryProd);
        productCategory.setId_product(product);
        productCategoryRepository.save(productCategory);
        return "redirect:/createNewProduct";
    }

    @GetMapping("/update-product-categories")
    public ResponseEntity<Map<String, List<?>>> getProductsAndCategories() {
        List<Product> products = productRepository.findAll();
        List<CategoryProd>  categoryProds = categoryProdReposittory.findAll();
        Map<String, List<?>> response = new HashMap<>();
        response.put("product", products);
        response.put("categoryProd", categoryProds);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/createNewProduct")
    public String createUserView(Model model) {
        List<Product> products = productRepository.findAll();
        List<CategoryProd>  categoryProds = categoryProdReposittory.findAll();

        model.addAttribute("product", products);
        model.addAttribute("categoryProd", categoryProds);
        return "newProduct";

    }
}
