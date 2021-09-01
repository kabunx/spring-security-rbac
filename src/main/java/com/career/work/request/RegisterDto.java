package com.career.work.request;

import com.career.work.annotation.CareerPassword;
import com.career.work.annotation.CareerUsername;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class RegisterDto implements Serializable {
    @NotNull
    @NotBlank(message = "用户名不能为空")
    @CareerUsername
    private String username;

    @NotNull
    @NotBlank(message = "密码不能为空")
    @CareerPassword
    private String password;

    @NotNull
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;
}
