package com.career.work.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class LoginDto implements Serializable {

    @NotNull
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotNull
    @NotBlank(message = "登陆密码不能为空")
    private String password;
}
