package com.career.work.controller;

import com.career.work.request.AuthRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public AuthRequest register(@Valid @RequestBody AuthRequest authRequest) {
        return authRequest;
    }

    @PostMapping("/login")
    public String login() {
        return "login";
    }
}
