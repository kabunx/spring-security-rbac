package com.career.work.model.dao;

import com.career.work.model.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    Role selectWithPermissionsByPrimaryKey(Long id);

    List<Role> selectByUserId(Long userId);

    List<Role> selectWithPermissionsByIds(List<Long> ids);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}