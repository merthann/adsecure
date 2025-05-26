package com.adsecure.webapp.service;

import com.adsecure.webapp.dtos.UserRegisterRequest;

public interface UserService {
    void registerUser(UserRegisterRequest request);
}
