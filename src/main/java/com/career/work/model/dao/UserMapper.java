package com.career.work.model.dao;

import com.career.work.model.Role;
import com.career.work.model.User;

import java.util.Date;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    int insertRole(User user, Role role, Date date);

    User selectByPrimaryKey(Long id);

    User selectByUsername(String username);

    User selectByUsernameWithPermissions(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}