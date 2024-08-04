package com.cb.controller;

import com.cb.model.*;
import com.cb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class NewServicesController {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private CategoryReposittory categoryReposittory;
    @Autowired
    private CategoryServiceRepository categoryServiceRepository;

    @PostMapping("/createService")
    public String  createService(@RequestParam String name_service, @RequestParam Double price,@RequestParam String description,@RequestParam("duration")
    @DateTimeFormat(pattern = "HH:mm") LocalTime duration) {
        Services services= new Services();
        services.setName_service(name_service);
        services.setPrice(price);
        services.setDescription(description);
        services.setDuration(duration);
        serviceRepository.save(services);
        return "redirect:/createService";
        // return "redirect:/createService";
    }
    @PostMapping("/createCategory")
    public String createCategory(@RequestParam String name_category) {
        Category category= new Category();
        category.setName_category(name_category);

        categoryReposittory.save(category);
        return "redirect:/createService";
    }
    @PostMapping("/createCategoryService")
    public String createCategoryService(@RequestParam Integer id_service,@RequestParam Integer id_category ) {
        CategoryServices categoryServices= new CategoryServices();
        Services service = new Services();
        service.setId_service(id_service);

        Category category = new Category();
        category.setId_category(id_category);
        categoryServices.setId_service(service);
        categoryServices.setId_category(category);
        categoryServiceRepository.save(categoryServices);
        return "redirect:/createService";
    }
    @GetMapping("/update-services-categories")
    public ResponseEntity<Map<String, List<?>>> getServicesAndCategories() {
        List<Services> services = serviceRepository.findAll();
        List<Category> categories = categoryReposittory.findAll();
        Map<String, List<?>> response = new HashMap<>();
        response.put("services", services);
        response.put("categories", categories);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/createService")
    public String createUserView(Model model) {
        List<Services> services = serviceRepository.findAll();
        List<Category>  categories = categoryReposittory.findAll();

        model.addAttribute("service", services);
        model.addAttribute("category", categories);
        return "newService";

    }
}
