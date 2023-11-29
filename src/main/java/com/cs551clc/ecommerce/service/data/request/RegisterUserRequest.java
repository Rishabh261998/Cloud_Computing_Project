package com.cs551clc.ecommerce.service.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    private String username;
    private String full_name;
    private String email;
    private String password;
}
