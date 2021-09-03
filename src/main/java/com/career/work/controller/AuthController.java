package com.career.work.controller;

import com.career.work.exception.WorkException;
import com.career.work.request.LoginDto;
import com.career.work.request.RegisterDto;
import com.career.work.response.JsonResponse;
import com.career.work.service.AuthService;
import com.career.work.service.LoginService;
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

    @Resource
    LoginService loginService;

    @PostMapping("/register")
    public JsonResponse<Object> register(@Valid @RequestBody RegisterDto registerDto) {
        authService.register(registerDto);
        return JsonResponse.success();
    }

    @PostMapping("/login")
    public JsonResponse<Object> login(@Valid @RequestBody LoginDto loginDto) throws WorkException {
        return JsonResponse.success(
                loginService.login(loginDto)
        );
    }
}
