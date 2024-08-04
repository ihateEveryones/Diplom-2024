package com.cb.controller;

import com.cb.model.CategoryProd;
import com.cb.model.Roles;
import com.cb.model.User;
import com.cb.repository.RoleRepository;
import com.cb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Controller

public class UserEditDeleteController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/editUser")
    public String editUser(@RequestParam Integer id_user, @RequestParam String user_email, @RequestParam String surname,
                           @RequestParam String user_name, @RequestParam String patronymic, @RequestParam String phone_number,
                           @RequestParam Integer user_id_role) {
        Optional<User> userId = userRepository.findById(id_user);
        User user = userId.get();
        user.setEmail(user_email);
        user.setSurname(surname);
        user.setName(user_name);
        user.setPatronymic(patronymic);
        user.setPhone_number(phone_number);
        Roles roles = new Roles();
        roles.setId_roles(user_id_role);
        user.setId_roles(roles);
        userRepository.save(user);
        return "redirect:/editDeleteUser";

    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Integer id_user) {
        Optional<User> userId = userRepository.findById(id_user);
        User user = userId.get();
        user.setDelete(true);
        userRepository.save(user);
        return "redirect:/editDeleteUser";

    }

    @GetMapping("/editDeleteUser")
    public String editDeleteUser(Model model) {
        List<User> users = userRepository.getAllUser();
        List<Roles> roles = roleRepository.findAllRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "EditDeleteUser";
    }
}
