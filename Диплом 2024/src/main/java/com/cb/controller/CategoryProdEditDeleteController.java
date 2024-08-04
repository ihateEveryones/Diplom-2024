package com.cb.controller;


import com.cb.model.CategoryProd;
import com.cb.repository.CategoryProdReposittory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Controller
public class CategoryProdEditDeleteController {
    @Autowired
    private CategoryProdReposittory productCategoryRepository;
    @Autowired
    private CategoryProdReposittory categoryProdReposittory;

    @PostMapping("/editProductCategory")
    public String editUser(@RequestParam Integer id, @RequestParam String name_category) {
        Optional<CategoryProd> product = categoryProdReposittory.findById(id);
        CategoryProd categoryProd = product.get();
        categoryProd.setName_category(name_category);
        categoryProdReposittory.save(categoryProd);
        return "redirect:/editDeleteProductCategory";
    }

    @PostMapping("/deleteProductCategory")
    public String deleteUser(@RequestParam Integer id) {

        Optional<CategoryProd> product = categoryProdReposittory.findById(id);
        CategoryProd categoryProd = product.get();
        categoryProd.setDelete(true);
        categoryProdReposittory.save(categoryProd);
        return "redirect:/editDeleteProductCategory";

    }

    @GetMapping("/editDeleteProductCategory")
    public String userView(Model model, @AuthenticationPrincipal UserDetails userDetails,
                           @RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder) {
        Sort sort = Sort.by("id_category");
        if (sortOrder.equals("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        List<CategoryProd> productCategories = categoryProdReposittory.findAllSorted(sort);
        model.addAttribute("productCategories", productCategories);
        model.addAttribute("sortOrder", sortOrder);
        return "EditCategoryProducts";

    }
}
