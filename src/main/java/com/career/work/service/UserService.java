package com.career.work.service;

import com.career.work.model.Permission;
import com.career.work.model.User;
import com.career.work.model.dao.PermissionMapper;
import com.career.work.model.dao.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    PermissionMapper permissionMapper;

    public List<User> getWithPermissionsByIds(List<Long> ids) {
        List<User> users = userMapper.selectByIds(ids);
        List<Permission> permissions = permissionMapper.selectByUserIds(ids);
        users.forEach(user -> {
            List<Permission> ps = permissions
                    .stream()
                    .filter(p -> p.getUserId().equals(user.getId())).collect(Collectors.toList());
            user.setPermissions(ps);
        });

        return users;
    }
}
