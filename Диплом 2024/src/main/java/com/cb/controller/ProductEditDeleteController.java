package com.cb.controller;


import com.cb.model.Product;
import com.cb.repository.CategoryProdReposittory;
import com.cb.repository.ProductRepository;
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
public class ProductEditDeleteController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryProdReposittory categoryProdReposittory;
    @PostMapping("/editProduct")
    public String editUser(@RequestParam Integer id, @RequestParam String name_product, @RequestParam Double price,@RequestParam String description) {
        Optional<Product> product= productRepository.findById(id);
        Product prod= product.get();
        prod.setName_product(name_product);
        prod.setDescription(description);
        prod.setPrice(price);
        productRepository.save(prod);
        return "redirect:/editDeleteProduct";

    }
    @PostMapping("/deleteProduct")
    public String deleteUser(@RequestParam Integer id) {

        Optional<Product> product= productRepository.findById(id);

        Product prod= product.get();
        prod.setDelete(true);
        productRepository.save(prod);
        return "redirect:/editDeleteProduct";

    }
    @GetMapping("/editDeleteProduct")
    public String userView(Model model,@AuthenticationPrincipal UserDetails userDetail,
                           @RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder,
                           @RequestParam(value = "sortField", defaultValue = "id_product") String sortField) {
        Sort sort = Sort.by(sortField);
        if (sortOrder.equals("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        List<Product> products = productRepository.findAll(sort);

        model.addAttribute("product", products);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("sortField", sortField);
        return "EditDeleteProducts";

    }
}
