package com.cb.controller;

import com.cb.model.*;
import com.cb.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

@Controller

public class HomePageController {
    @Autowired
    private CategoryServiceRepository categoryServiceRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private CategoryProdReposittory categoryProdReposittory;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CategoryReposittory category;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/homepage")
    public String registrationForm1(Model model,@AuthenticationPrincipal UserDetails userDetails) throws JsonProcessingException {
        if (userDetails != null) {
            User user = userRepository.findByEmail(userDetails.getUsername());
            model.addAttribute("image", user.getImage());
            // остальной код
        }

       // List<String> times = userRepository.getAllTimes();
        ObjectMapper objectMapper = new ObjectMapper();
       // String timesJson = objectMapper.writeValueAsString(times);
        List<User> master = userRepository.getAllMaster();
        List<User> masters = userRepository.getAllMaster();
        List<Category> categories = category.findAll();
        List<Object[]>  top3Master = orderRepository.findTop3MasterWithMostOrders();
        List<Object[]>  minPrice = categoryServiceRepository.getMinPricesByCategory();
        List<Object[]>  top4PopularCategories = productCategoryRepository.findTop4PopularCategories();
        List<CategoryServices> cat = categoryServiceRepository.getCategoryServices();
        model.addAttribute("cats", cat);
        Map<Integer, String> formattedDurations = new HashMap<>();

        // Проходим по каждой услуге и форматируем длительность
        for (CategoryServices service : cat) {
            LocalTime duration = service.getId_service().getDuration();
            int minutes = duration.getHour() * 60 + duration.getMinute();
            String formattedDuration = formatDuration(minutes);
            formattedDurations.put(service.getId_service().getId_service(), formattedDuration);
        }

        List<Object> orders = orderRepository.findByMasterId();
        model.addAttribute("masters", masters);
        model.addAttribute("top3Master", top3Master);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("top4PopularCategories", top4PopularCategories);
       // model.addAttribute("times", timesJson);
        model.addAttribute("orders", orders);
        System.out.println(cat);
        model.addAttribute("categories", categories);
        model.addAttribute("master", master);
        List<CategoryProd> categoriesProd = categoryProdReposittory.findAll();
        List<ProductCategory> prod = productCategoryRepository.getCategoryProduct();
        model.addAttribute("cats", prod);
        model.addAttribute("formattedDurations", formattedDurations);

        model.addAttribute("categoriesProd", categoriesProd);

        return "homePage";
    }
    private String formatDuration(int duration) {
        if (duration >= 60) {
            int hours = duration / 60;
            int remainingMinutes = duration % 60;
            if (remainingMinutes == 0) {
                return hours + " ч.";
            } else {
                return hours + " ч. " + remainingMinutes + " мин.";
            }
        } else {
            return duration + " мин.";
        }
    }
    @GetMapping("/servicesPage")
    public String ServPAge(Model model,@AuthenticationPrincipal UserDetails userDetails) throws JsonProcessingException {
        if (userDetails != null) {
            User user = userRepository.findByEmail(userDetails.getUsername());
            model.addAttribute("image", user.getImage());
            // остальной код
        }
        // List<String> times = userRepository.getAllTimes();
        ObjectMapper objectMapper = new ObjectMapper();
        // String timesJson = objectMapper.writeValueAsString(times);
        List<User> master = userRepository.getAllMaster();
        List<User> masters = userRepository.getAllMaster();
        List<Category> categories = category.findAll();
        List<CategoryServices> cat = categoryServiceRepository.getCategoryServices();
        model.addAttribute("cats", cat);
        Map<Integer, String> formattedDurations = new HashMap<>();

        // Проходим по каждой услуге и форматируем длительность
        for (CategoryServices service : cat) {
            LocalTime duration = service.getId_service().getDuration();
            int minutes = duration.getHour() * 60 + duration.getMinute();
            String formattedDuration = formatDuration(minutes);
            formattedDurations.put(service.getId_service().getId_service(), formattedDuration);
        }

        List<Object> orders = orderRepository.findByMasterId();
        model.addAttribute("masters", masters);
        // model.addAttribute("times", timesJson);
        model.addAttribute("orders", orders);
        System.out.println(cat);
        model.addAttribute("categories", categories);
        model.addAttribute("master", master);
        List<CategoryProd> categoriesProd = categoryProdReposittory.findAll();
        List<ProductCategory> prod = productCategoryRepository.getCategoryProduct();
        model.addAttribute("cats", prod);
        model.addAttribute("formattedDurations", formattedDurations);

        model.addAttribute("categoriesProd", categoriesProd);
        return "ServicesPageCours";
    }

    @GetMapping("/productPage")
    public String ProductPAge(Model model,@AuthenticationPrincipal UserDetails userDetails) throws JsonProcessingException {
        if (userDetails != null) {
            User user = userRepository.findByEmail(userDetails.getUsername());
            model.addAttribute("image", user.getImage());
            // остальной код
        }
        // List<String> times = userRepository.getAllTimes();
        ObjectMapper objectMapper = new ObjectMapper();
        // String timesJson = objectMapper.writeValueAsString(times);
        List<User> master = userRepository.getAllMaster();
        List<User> masters = userRepository.getAllMaster();
        List<Category> categories = category.findAll();
        List<CategoryServices> cat = categoryServiceRepository.getCategoryServices();
        model.addAttribute("cats", cat);
        Map<Integer, String> formattedDurations = new HashMap<>();

        // Проходим по каждой услуге и форматируем длительность
        for (CategoryServices service : cat) {
            LocalTime duration = service.getId_service().getDuration();
            int minutes = duration.getHour() * 60 + duration.getMinute();
            String formattedDuration = formatDuration(minutes);
            formattedDurations.put(service.getId_service().getId_service(), formattedDuration);
        }

        List<Object> orders = orderRepository.findByMasterId();
        model.addAttribute("masters", masters);
        // model.addAttribute("times", timesJson);
        model.addAttribute("orders", orders);
        System.out.println(cat);
        model.addAttribute("categories", categories);
        model.addAttribute("master", master);
        List<CategoryProd> categoriesProd = categoryProdReposittory.findAll();
        List<ProductCategory> prod = productCategoryRepository.getCategoryProduct();
        model.addAttribute("cats", prod);
        model.addAttribute("formattedDurations", formattedDurations);

        model.addAttribute("categoriesProd", categoriesProd);
        return "ProductsPageCours";
    }
    @GetMapping("/product/{id}")
    public String productPage(@PathVariable("id") Integer id, Model model) throws JsonProcessingException {

        Optional<Product> productOptional  = productRepository.findById(id);
        if(productOptional.isEmpty()) {
            // Если продукт не существует, можно перенаправить на другую страницу или вернуть ошибку 404
            return "error404"; // Имя шаблона страницы ошибки 404
        }
        Product product = productOptional.get();
        // Добавляем полученные данные в модель
        model.addAttribute("product", product);

        return "ProductPage";
    }
    @GetMapping("/service/{id}")
    public String servicePage(@PathVariable("id") Integer id, Model model) throws JsonProcessingException {

        Optional<Services> serviceOptional  = serviceRepository.findById(id);
        if(serviceOptional.isEmpty()) {
            // Если продукт не существует, можно перенаправить на другую страницу или вернуть ошибку 404
            return "error404"; // Имя шаблона страницы ошибки 404
        }
        Services service = serviceOptional.get();
        // Добавляем полученные данные в модель
        model.addAttribute("service", service);

        return "ServicePage";
    }
    @GetMapping("/master/{id}")
    public String masterPage(@PathVariable("id") Integer id, Model model) throws JsonProcessingException {

       Optional<User> userOptional   = userRepository.findById(id);
        if(userOptional==null) {
            // Если продукт не существует, можно перенаправить на другую страницу или вернуть ошибку 404
            return "error404"; // Имя шаблона страницы ошибки 404
        }
        User user = userOptional.get();
//        Product product = productOptional.get();
        // Добавляем полученные данные в модель
        model.addAttribute("master", user);

        return "MasterPage";
    }
}
