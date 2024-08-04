package com.cb.controller;

import com.cb.model.Category;
import com.cb.model.CategoryServices;
import com.cb.model.Services;
import com.cb.repository.CategoryReposittory;
import com.cb.repository.CategoryServiceRepository;
import com.cb.repository.OrderRepository;
import com.cb.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Controller
public class ReportsController {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private CategoryReposittory categoryReposittory;
    @Autowired
    private CategoryServiceRepository categoryServiceRepository;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/createReportsMaster")
    @ResponseBody
    public List<Object[]> createReportsMaster() {
        return orderRepository.getMasterOrderStatistics();
    }

    @GetMapping("/createReportsCategoryService")
    @ResponseBody
    public List<Object[]> createReportsCategoryService() {
        return orderRepository.getCategoryServiceStatistics();
    }

    @GetMapping("/createReportsDailySalesForYear")
    @ResponseBody
    public List<Object[]> createReportsDailySalesForYear(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                         @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return orderRepository.getDailySalesForPeriod(startDate, endDate);
    }

    @GetMapping("/createReports")
    public String createReports(Model model) {
        return "reports";

    }
}
