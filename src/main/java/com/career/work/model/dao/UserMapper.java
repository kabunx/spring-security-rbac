package com.career.work.model.dao;

import com.career.work.model.Role;
import com.career.work.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    int insertRole(@Param("user") User user, @Param("role") Role role);

    User selectByPrimaryKey(Long id);

    User selectByUsername(String username);

    User selectByUsernameWithPermissions(String username);

    List<User> selectByIds(List<Long> ids);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}