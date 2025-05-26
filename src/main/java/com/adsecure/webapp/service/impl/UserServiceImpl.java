package com.adsecure.webapp.service.impl;

import com.adsecure.webapp.dtos.UserRegisterRequest;
import com.adsecure.webapp.repositories.UserRepository;
import com.adsecure.webapp.repositories.entities.UserEntity;
import com.adsecure.webapp.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserRegisterRequest request) {
        if (userRepository.existsByMail(request.getMail())) {
            throw new RuntimeException("Bu e-posta adresi zaten kayıtlı!");
        }

        UserEntity user = UserEntity.builder()
                .fname(request.getFname())
                .lname(request.getLname())
                .password(passwordEncoder.encode(request.getPassword()))
                .gender(request.getGender())
                .phoneNo(request.getPhoneNo())
                .mail(request.getMail())
                .verified(false)
                .role("USER")
                .build();

        userRepository.save(user);
    }
}
