package com.career.work.model.dao;

import com.career.work.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    User selectByUsername(String username);

    User selectByUsernameWithPermissions(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}