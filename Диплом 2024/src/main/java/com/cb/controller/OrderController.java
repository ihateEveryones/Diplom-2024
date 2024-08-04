package com.cb.controller;

import com.cb.model.*;
import com.cb.repository.*;
import com.cb.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.*;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderInfoServicesRepository orderInfoServicesRepository;

    @Autowired
    private OrderInfoProductsRepository orderInfoProductsRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @PostMapping("/createOrder")
    public String createService(@RequestParam Integer id_master, @RequestParam List<Integer> id_category_services, @RequestParam("date")
    @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                @RequestParam("time")
                                @DateTimeFormat(pattern = "HH:mm") LocalTime time, @RequestParam("duration")
                                @DateTimeFormat(pattern = "HH:mm") List<LocalTime> ListDuration, @RequestParam Double totalPrice,
                                @RequestParam(required = false, defaultValue = "") List<Integer> id_product_category) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        LocalTime totalDuration = LocalTime.of(0, 0); // Инициализируем общую продолжительность

        for (LocalTime duration : ListDuration) {
            totalDuration = totalDuration.plusHours(duration.getHour()).plusMinutes(duration.getMinute());
        }
        User order_user = userRepository.findByEmail(email);
        Optional<User> order_master = userRepository.findById(id_master);
        Orders order = new Orders();
        order.setId_user(order_user);
        User user = order_master.get();
        order.setId_master(user);
        order.setDate(date);
        order.setStart_time(time);
        LocalTime newTime = time.plusHours(totalDuration.getHour()).plusMinutes(totalDuration.getMinute());
        order.setEnd_time(newTime);
        order.setTotal_price(totalPrice);
        orderRepository.save(order);
        for (Integer id_service : id_category_services) {
            OrderServicesInfo orderServicesInfo = new OrderServicesInfo();
            orderServicesInfo.setId_order(order);
            CategoryServices categoryService = new CategoryServices();
            categoryService.setId_category_services(id_service);
            orderServicesInfo.setId_category_services(categoryService);
            orderInfoServicesRepository.save(orderServicesInfo);
        }
        for (Integer prod_category : id_product_category) {
            OrderProductsInfo orderProductsInfo = new OrderProductsInfo();
            orderProductsInfo.setId_order(order);
            ProductCategory productCategory = new ProductCategory();
            productCategory.setId_product_category(prod_category);
            orderProductsInfo.setId_product_category(productCategory);
            orderInfoProductsRepository.save(orderProductsInfo);
        }
            return "redirect:/sendOrderInformation?orderId=" + order.getId_orders();
    }

    @GetMapping("/sendOrderInformation")
    public String sendOrderInformation(@RequestParam("orderId") Integer orderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        List<Orders> ordersList = orderRepository.findAllByIdOrders(orderId);
        Orders order = ordersList.get(0);
        List<OrderProductsInfo> orderProductsInfoList = orderInfoProductsRepository.getOrderProductListByOrderId(order);
        List<OrderServicesInfo> orderServicesInfoList = orderInfoServicesRepository.getOrderServicesListByOrderId(order);
        StringBuilder emailMessage = new StringBuilder();
        emailMessage.append("Спасибо за ваш заказ!\n\n")
                .append("Номер заказа: ").append(order.getId_orders()).append("\n")
                .append("Дата: ").append(order.getDate()).append("\n")
                .append("Время начала: ").append(order.getStart_time()).append("\n")
                .append("Время конца: ").append(order.getEnd_time()).append("\n")
                .append("Общая стоимость: ").append(order.getTotal_price()).append(" руб.\n\n")
                .append("Детали заказа:\n");

        for (OrderServicesInfo savedOrder : orderServicesInfoList) {
            emailMessage.append("Категория услуги ID: ").append(savedOrder.getId_category_services().getId_category_services()).append("\n");
        }
        emailMessage.append("\nТовары в заказе:\n");
        for (OrderProductsInfo orderProduct : orderProductsInfoList) {
            emailMessage.append("Категория товара ID: ").append(orderProduct.getId_product_category().getId_product_category()).append("\n");
        }
        emailService.sendSimpleMessage(email, "Заказ номер " + order.getId_orders(), emailMessage.toString());
        return "redirect:/homepage";
    }

    private Integer generateOrderId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }
}
