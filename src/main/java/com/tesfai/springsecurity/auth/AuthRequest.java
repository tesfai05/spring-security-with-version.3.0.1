package com.tesfai.springsecurity.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private  String username;
    private String password;
}
