package com.cb.controller;

import com.cb.model.*;
import com.cb.repository.*;
import com.cb.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller

public class ProfileController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderInfoProductsRepository orderInfoProductsRepository;

    @Autowired
    private OrderInfoServicesRepository orderInfoServicesRepository;
    @Autowired
    private ServiceRepository serviceRepository;


    @Value("${image.storage.path}")
    String storagePath;

    @GetMapping("/profile")
    public String Profile(Model model, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("name", user.getName());
        model.addAttribute("image", user.getImage());
        Integer id = user.getId();
        List<Object[]> results1 = orderRepository.findOrdersById(id);
        List<OrderServicesInfo> ServicesByUserId = orderInfoServicesRepository.findOrderServicesListByUserId(user);
        List<OrderServicesInfo> ServicesByMasterId = orderInfoServicesRepository.findOrderServicesListByMasterId(user);
        List<OrderProductsInfo> ProductsByUserId = orderInfoProductsRepository.findOrderProductListByUserId(user);
        List<OrderProductsInfo> ProductsByMasterId = orderInfoProductsRepository.findOrderProductListByMasterId(user);
        List<Orders> ordersByUserId = orderRepository.findOrdersByUserId(id);
        List<Orders> ordersByMasterId = orderRepository.findOrdersByMasterId(id);
        model.addAttribute("orders", ordersByUserId);
        model.addAttribute("ordersByMasterId", ordersByMasterId);
        model.addAttribute("ordersServices", ServicesByUserId);
        model.addAttribute("orderServicesByMasterId", ServicesByMasterId);
        model.addAttribute("ordersProducts", ProductsByUserId);
        model.addAttribute("ordersProducts", ProductsByUserId);
        return "profile";
    }

    @PostMapping("/upload")
    public String uploadProfileImage(Model model, @AuthenticationPrincipal UserDetails userDetails, @RequestParam("file") MultipartFile file) throws IOException {
        String uploadPath = "/image/";
        User user = userRepository.findByEmail(userDetails.getUsername());
        String fileName = "avatar_user" + user.getId() + "_" + file.getOriginalFilename();
        File dest = new File(storagePath + File.separator + fileName);
        file.transferTo(dest);
        user.setImage(uploadPath + fileName);
        userRepository.save(user);
        model.addAttribute("name", user.getName());
        return "redirect:/profile";
    }

    @PostMapping("/editProfile")
    @ResponseBody
    public String editUser(@RequestParam String user_name, Principal principal,
                           @RequestParam("newEmail") String newEmail,@RequestParam String surname,@RequestParam String patronymic,
                           @RequestParam String phoneNumber) {
        User user = userRepository.findByEmail(principal.getName());
        user.setName(user_name);
        String username = principal.getName();
        userService.changeEmail(username, newEmail, user_name,phoneNumber,surname,patronymic);

        return "redirect:/profile?success";

    }
}
