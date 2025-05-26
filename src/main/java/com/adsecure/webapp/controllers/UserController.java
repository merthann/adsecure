package com.adsecure.webapp.controllers;

import com.adsecure.webapp.dtos.UserRegisterRequest;
import com.adsecure.webapp.repositories.UserRepository;
import com.adsecure.webapp.repositories.VerificationTokenRepository;
import com.adsecure.webapp.repositories.entities.UserEntity;
import com.adsecure.webapp.repositories.entities.VerificationToken;
import com.adsecure.webapp.service.UserService;
import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;

    public UserController(UserService userService, UserRepository userRepository, VerificationTokenRepository tokenRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        userService.registerUser(request);
        return ResponseEntity.ok("Kayıt başarılı! Lütfen e-posta adresinizi doğrulayın.");
    }
    
    @GetMapping("/verify")
    public ResponseEntity<String> verifyAccount(@RequestParam String token) {
        Optional<VerificationToken> optionalToken = tokenRepository.findByToken(token);
        if (optionalToken.isEmpty()) {
            return ResponseEntity.badRequest().body("Geçersiz veya süresi dolmuş doğrulama bağlantısı.");
        }

        UserEntity user = optionalToken.get().getUser();
        user.setVerified(true);
        userRepository.save(user);
        tokenRepository.delete(optionalToken.get());

        return ResponseEntity.ok("Hesabınız başarıyla doğrulandı!");
    }

}
