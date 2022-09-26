package com.findjb.findjob.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.Data;

@Data
public class CreateEnterprise {
    @NotNull(message = "Tên không được để trống")
    @Size(min = 4, max = 200, message = "Tên phải có độ dài từ {min} đến {max} kí tự")
    private String name;

    @NotNull(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotNull(message = "Mật khẩu không được để trống")
    @Min(value = 8, message = "Mật khẩu không phải có ít nhất {value} kí tự")
    private String password;

    @NotNull(message = "Địa chỉ không được để trống")
    private String address;
}
