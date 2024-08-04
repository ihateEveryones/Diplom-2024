package com.cb.controller;

import com.cb.dto.UserDto;
import com.cb.model.User;
import com.cb.service.EmailService;
import com.cb.service.UserService;
import com.cb.service.VerificationCodeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private VerificationCodeService verificationCodeService;
    @Autowired
    private EmailService emailService;

    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public ResponseEntity<Map<String, String>> registration(
            @Valid @RequestBody UserDto userDto,
            BindingResult result,
            Model model) {
        Map<String, String> response = new HashMap<>();
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        System.out.println(userDto.getVerificationCode());
        if (existingUser != null) {
            result.rejectValue("email", null, "User already registered !!!");
            response.put("error", "User already registered");
            return ResponseEntity.badRequest().body(response);
        }

        if (result.hasErrors()) {
            response.put("error", "Validation errors");
            return ResponseEntity.badRequest().body(response);
        }
        if (!verificationCodeService.verifyCode(userDto.getEmail(),userDto.getVerificationCode())) {
            response.put("error", "Неправильный код подтверждения");
            return ResponseEntity.badRequest().body(response);
        }
        userService.saveUser(userDto);
        verificationCodeService.removeCode(userDto.getEmail());
        response.put("message", "Вы зарегистрировались!");
        response.put("redirect", "/login");
        return ResponseEntity.ok(response);
    }
    @PostMapping("/registration/send-verification-code")
    public ResponseEntity<Map<String, String>> sendVerificationCodeRegistration(@RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        String email = request.get("email");
        if (email == null || email.isEmpty()) {
            response.put("error", "Email address is required");
            return ResponseEntity.badRequest().body(response);
        }

        verificationCodeService.generateAndSendCode(email, emailService);
        return ResponseEntity.ok(response);
    }
}
