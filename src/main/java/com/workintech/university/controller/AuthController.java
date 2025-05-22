package com.workintech.university.controller;

import com.workintech.university.dto.RegisterRequestDto;
import com.workintech.university.dto.RegisterResponseDto;
import com.workintech.university.entity.User;
import com.workintech.university.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponseDto register(@Validated @RequestBody RegisterRequestDto requestDto){
        User user = authService.register(requestDto.getEmail(), requestDto.getPassword());

        return new RegisterResponseDto(requestDto.getEmail(), "Kullanıcı başarılı bir şekilde kaydedildi");
    }

}
