package com.cb.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class VerificationCodeService {

    private final Map<String, String> verificationCodes = new HashMap<>();

    public void generateAndSendCode(String email, EmailService emailService) {
        String verificationCode = String.valueOf(new Random().nextInt(899999) + 100000);
        verificationCodes.put(email, verificationCode);
        emailService.sendSimpleMessage(email, "Код подтверждения", "Ваш код подтверждения: " + verificationCode);
    }

    public boolean verifyCode(String email, String code) {
        return code != null && code.equals(verificationCodes.get(email));
    }

    public void removeCode(String email) {
        verificationCodes.remove(email);
    }
}
