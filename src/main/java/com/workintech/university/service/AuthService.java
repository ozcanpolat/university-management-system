package com.workintech.university.service;

import com.workintech.university.entity.User;

public interface AuthService {
    User register(String email, String password);
}
