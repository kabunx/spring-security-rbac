package com.career.work.service;

import com.career.work.model.Permission;
import com.career.work.model.Role;
import com.career.work.model.User;
import com.career.work.model.dao.PermissionMapper;
import com.career.work.model.dao.RoleMapper;
import com.career.work.model.dao.UserMapper;
import com.career.work.service.contract.AuthServiceInterface;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AuthService implements AuthServiceInterface, UserDetailsService {

    private static final String ROLE_PREFIX = "ROLE_";

    @Resource
    UserMapper userMapper;

    @Resource
    RoleMapper roleMapper;

    @Resource
    PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 加载用户角色
        List<Role> roles = roleMapper.selectByUserId(user.getId());
        // 将角色角色并添加到authorities
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> {
            String roleName = role.getName();
            if (!roleName.startsWith(ROLE_PREFIX)) {
                roleName = ROLE_PREFIX + roleName;
            }
            authorities.add(new SimpleGrantedAuthority(roleName));
        });
        List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        // 获取用户角色中包含的权限
        List<Permission> rps = permissionMapper.selectByRoleIds(roleIds);
        // 加载用户直属权限
        List<Permission> ups = permissionMapper.selectByUserId(user.getId());
        // 合并去重后添加到authorities
        authorities.addAll(
                Stream.of(rps, ups)
                        .flatMap(Collection::stream)
                        .distinct()
                        .collect(Collectors.toList())
        );

        user.setAuthorities(authorities);

        return user;
    }
}
