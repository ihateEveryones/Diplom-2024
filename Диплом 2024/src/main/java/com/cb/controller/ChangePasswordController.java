package com.cb.controller;

import com.cb.model.User;
import com.cb.repository.UserRepository;
import com.cb.service.EmailService;
import com.cb.service.UserService;
import com.cb.service.UserServiceImpl;
import com.cb.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class ChangePasswordController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationCodeService verificationCodeService;
//    private Map<String, String> verificationCodes = new HashMap<>();

    @PostMapping("/changePassword/send-verification-code")
    public ResponseEntity<Map<String, String>> sendVerificationCode(@RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        String email = request.get("email");
        User findByEmail = userRepository.findByEmail(email);
        if (email == null || email.isEmpty()) {
            response.put("error", "Email address is required");
            return ResponseEntity.badRequest().body(response);
        } else if (findByEmail == null) {
            response.put("error", "Такого email не существует.");
            return ResponseEntity.badRequest().body(response);

        }
        verificationCodeService.generateAndSendCode(email, emailService);
        return ResponseEntity.ok(response);
    }
//    @PostMapping("/registration/send-verification-code")
//    public ResponseEntity<Map<String, String>> sendVerificationCodeRegistration(@RequestBody Map<String, String> request) {
//        Map<String, String> response = new HashMap<>();
//        String email = request.get("email");
//        if (email == null || email.isEmpty()) {
//            response.put("error", "Email address is required");
//            System.out.println("maila netuy");
//            return ResponseEntity.badRequest().body(response);
//        }
//        verificationCodeService.generateAndSendCode(email, emailService);
//        return ResponseEntity.ok(response);
//    }
    @PostMapping("/changePassword/verify-and-change-password")
    public ResponseEntity<Map<String, Object>> verifyAndChangePassword(@RequestBody Map<String, String> request, Principal principal) {
        String currentPassword = request.get("currentPassword");
        String newPassword = request.get("newPassword");
        String confirmNewPassword = request.get("confirmNewPassword");
        String verificationCode = request.get("verificationCode");
        String email;
        String username;
        if (principal != null) {
            email = principal.getName();
        } else {
            email = request.get("email");
        }
        Map<String, Object> response = new HashMap<>();
        if (!newPassword.equals(confirmNewPassword)) {
            response.put("success", false);
            response.put("message", "Пароли не совпадают");
            return ResponseEntity.ok(response);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            username = authentication.getName();
        } else {
            username = request.get("email");
        }
        User user = userRepository.findByEmail(username);

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            response.put("success", false);
            response.put("message", "Текущий пароль не правильный");
            return ResponseEntity.ok(response);
        }
        if (!verificationCodeService.verifyCode(email, verificationCode)) {
            response.put("success", false);
            response.put("message", "Неправильный код подтверждения");
            return ResponseEntity.ok(response);
        }
        boolean success = userService.changePassword(email, currentPassword, newPassword);
        if (success) {
            response.put("success", true);
            verificationCodeService.removeCode(email);
        } else {
            response.put("success", false);
            response.put("message", "Неправильный текущий пароль");
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/changePassword")
    public String loginForm() {
        return "changePassword";
    }
}
