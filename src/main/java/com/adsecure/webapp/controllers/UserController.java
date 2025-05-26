package com.adsecure.webapp.controllers;

import com.adsecure.webapp.dtos.UserRegisterRequest;
import com.adsecure.webapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        userService.registerUser(request);
        return ResponseEntity.ok("Kayıt başarılı! Lütfen e-posta adresinizi doğrulayın.");
    }
}
