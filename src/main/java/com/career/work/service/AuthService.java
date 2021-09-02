package com.career.work.service;

import com.career.work.model.dao.UserMapper;
import com.career.work.request.LoginDto;
import com.career.work.request.RegisterDto;
import com.career.work.security.userdetails.UserDetailsServiceImpl;
import com.career.work.service.contract.AuthServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthService implements AuthServiceInterface {

    @Resource
    ObjectMapper objectMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    UserDetailsServiceImpl userDetailsService;

    public void register(RegisterDto registerDto) {

    }

    public UserDetails login(LoginDto loginDto) {
        return userDetailsService.loadUserByUsername(loginDto.getUsername());
    }
}
