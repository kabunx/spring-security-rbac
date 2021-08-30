package com.career.work.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class User implements UserDetails {
    private Long id;

    private String username;

    private String email;

    private String name;

    private Byte sex;

    private String avatar;

    private String phone;

    private String status;

    private String password;

    private Date passwordExpiredAt;

    private String source;

    private Date enabledAt;

    private Date expiredAt;

    private Date lockedAt;

    private String rememberToken;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        if (expiredAt == null) {
            return true;
        }
        return expiredAt.before(new Date());
    }

    @Override
    public boolean isAccountNonLocked() {
        if (lockedAt == null) {
            return true;
        }
        return lockedAt.before(new Date());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if (passwordExpiredAt == null) {
            return true;
        }
        return passwordExpiredAt.before(new Date());
    }

    @Override
    public boolean isEnabled() {
        if (expiredAt == null) {
            return true;
        }
        return expiredAt.before(new Date());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getPasswordExpiredAt() {
        return passwordExpiredAt;
    }

    public void setPasswordExpiredAt(Date passwordExpiredAt) {
        this.passwordExpiredAt = passwordExpiredAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Date getEnabledAt() {
        return enabledAt;
    }

    public void setEnabledAt(Date enabledAt) {
        this.enabledAt = enabledAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Date getLockedAt() {
        return lockedAt;
    }

    public void setLockedAt(Date lockedAt) {
        this.lockedAt = lockedAt;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken == null ? null : rememberToken.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}