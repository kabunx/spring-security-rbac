package com.career.work.security.userdetails;

import com.career.work.model.User;
import com.career.work.model.dao.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

public class UserDetailsPasswordServiceImpl implements UserDetailsPasswordService {

    @Resource
    UserMapper userMapper;

    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails updatePassword(UserDetails userDetails, String newPassword) {
        User user = userMapper.selectByUsername(userDetails.getUsername());
        user.setPassword(passwordEncoder.encode(newPassword));
        userMapper.updateByPrimaryKeySelective(user);
        return null;
    }
}
