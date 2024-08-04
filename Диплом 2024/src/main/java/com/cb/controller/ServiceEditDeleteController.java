package com.cb.controller;


import com.cb.model.Services;
import com.cb.repository.CategoryProdReposittory;
import com.cb.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@Controller
public class ServiceEditDeleteController {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private CategoryProdReposittory categoryProdReposittory;
    @PostMapping("/editService")
    public String editUser(@RequestParam Integer id, @RequestParam String name_service, @RequestParam Double price,@RequestParam LocalTime duration,
                           @RequestParam String description) {
        Optional<Services> services= serviceRepository.findById(id);
        Services services1= services.get();
        services1.setDuration(duration);
        services1.setDescription(description);
        services1.setName_service(name_service);
        services1.setPrice(price);
        serviceRepository.save(services1);
        return "redirect:/editDeleteService";

    }
    @PostMapping("/deleteService")
    public String deleteUser(@RequestParam Integer id) {

        Optional<Services> services= serviceRepository.findById(id);
        Services services1= services.get();
        services1.setDelete(true);
        serviceRepository.save(services1);
        return "redirect:/editDeleteService";

    }
    @GetMapping("/editDeleteService")
    public String sortServiceByIdAndPrice(Model model,@AuthenticationPrincipal UserDetails userDetails,
                           @RequestParam(value = "sortOrderService", defaultValue = "asc") String sortOrderService,
                           @RequestParam(value = "sortFieldService", defaultValue = "id_service") String sortFieldService) {
        Sort sort1 = Sort.by(sortFieldService);
        if (sortOrderService.equals("desc")) {
            sort1 = sort1.descending();
        } else {
            sort1 = sort1.ascending();
        }
        List<Services> services = serviceRepository.findAllSorted(sort1);
        model.addAttribute("services", services);
        model.addAttribute("sortOrderService", sortOrderService);
        model.addAttribute("sortFieldService", sortFieldService);
        return "EditDeleteServices";

    }
}
