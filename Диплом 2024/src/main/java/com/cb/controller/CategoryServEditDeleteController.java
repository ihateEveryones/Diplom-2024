package com.cb.controller;


import com.cb.model.Category;
import com.cb.model.CategoryProd;
import com.cb.repository.CategoryReposittory;
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
public class CategoryServEditDeleteController {

    @Autowired
    private CategoryReposittory categoryReposittory;

    @PostMapping("/editServiceCategory")
    public String editUser(@RequestParam Integer id, @RequestParam String name_category) {
        Optional<Category> category = categoryReposittory.findById(id);
        Category cat = category.get();
        cat.setName_category(name_category);
        categoryReposittory.save(cat);
        return "redirect:/editDeleteServiceCategory";

    }

    @PostMapping("/deleteServiceCategory")
    public String deleteUser(@RequestParam Integer id) {

        Optional<Category> category = categoryReposittory.findById(id);
        Category cat = category.get();
        cat.setDelete(true);
        categoryReposittory.save(cat);
        return "redirect:/editDeleteServiceCategory";

    }

    @GetMapping("/editDeleteServiceCategory")
    public String userView(Model model, @AuthenticationPrincipal UserDetails userDetails,
                           @RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder) {

        Sort sort = Sort.by("id_category");
        if (sortOrder.equals("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        List<Category> productCategories = categoryReposittory.findAllSorted(sort);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("categories", productCategories);

        return "EditCategoryServices";

    }
}
