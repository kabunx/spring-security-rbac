package com.career.work.service;

import com.career.work.model.User;
import com.career.work.model.dao.PermissionMapper;
import com.career.work.model.dao.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    PermissionMapper permissionMapper;

    public void store(User user) {

    }

}
