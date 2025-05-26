package com.adsecure.webapp.service.impl;

import com.adsecure.webapp.dtos.UserRegisterRequest;
import com.adsecure.webapp.repositories.UserRepository;
import com.adsecure.webapp.repositories.VerificationTokenRepository;
import com.adsecure.webapp.repositories.entities.UserEntity;
import com.adsecure.webapp.repositories.entities.VerificationToken;
import com.adsecure.webapp.service.EmailService;
import com.adsecure.webapp.service.UserService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;
    private final EmailService emailService;
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
        

     // TOKEN OLUŞTUR
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusHours(24))
                .build();

        tokenRepository.save(verificationToken);

        String verifyUrl = "http://localhost:8080/user/verify?token=" + token;
        String subject = "E-posta Doğrulama";
        String body = "Lütfen e-posta adresinizi doğrulamak için aşağıdaki bağlantıya tıklayın:\n" + verifyUrl;

        emailService.sendEmail(user.getMail(), subject, body);
    }
}
