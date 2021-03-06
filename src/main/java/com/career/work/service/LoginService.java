package com.career.work.service;

import com.career.work.exception.ExceptionEnum;
import com.career.work.exception.WorkException;
import com.career.work.model.Permission;
import com.career.work.model.Role;
import com.career.work.model.User;
import com.career.work.model.dao.PermissionMapper;
import com.career.work.model.dao.RoleMapper;
import com.career.work.model.dao.UserMapper;
import com.career.work.request.LoginDto;
import com.career.work.util.JwtUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LoginService implements UserDetailsService {

    private static final String ROLE_PREFIX = "ROLE_";

    @Resource
    UserMapper userMapper;

    @Resource
    RoleMapper roleMapper;

    @Resource
    PermissionMapper permissionMapper;

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.selectByUsername(username);
    }

    /**
     * @param loginDto 登录信息
     * @return 登录用户
     * @throws WorkException 登录异常
     */
    public User login(LoginDto loginDto) throws WorkException {
        User user = userMapper.selectByUsername(loginDto.getUsername());
        if (user == null) {
            throw new WorkException(ExceptionEnum.USER_LOGIN_ERROR);
        }
        // 密码判断
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new WorkException(ExceptionEnum.USER_LOGIN_ERROR);
        }
        // 加载用户角色
        user.setAuthorities(getUserAuthorities(user));
        return user;
    }

    /**
     * 将角色角色并添加到authorities
     * @param user 用户
     * @return 权限
     */
    private List<GrantedAuthority> getUserAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = roleMapper.selectByUserId(user.getId());
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
        authorities.addAll(mergePermissions(ups, rps));
        return authorities;
    }

    private List<Permission> mergePermissions(
            List<Permission> uPermissions,
            List<Permission> rPermissions
    ) {
        return Stream.of(uPermissions, rPermissions)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}
