package com.career.work.service;

import com.career.work.model.dao.UserMapper;
import com.career.work.service.contract.AuthServiceInterface;

import javax.annotation.Resource;

public class AuthService implements AuthServiceInterface {

    @Resource
    UserMapper userMapper;

    public void register() {

    }

    public void login() {
    }
}
