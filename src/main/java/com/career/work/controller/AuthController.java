package com.career.work.controller;

import com.career.work.request.LoginDto;
import com.career.work.request.RegisterDto;
import com.career.work.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    AuthService authService;

    @PostMapping("/register")
    public RegisterDto register(@Valid @RequestBody RegisterDto registerDto) {

        return registerDto;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {

        return "login";
    }
}
