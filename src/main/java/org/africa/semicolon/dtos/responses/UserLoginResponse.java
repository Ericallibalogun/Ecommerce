package org.africa.semicolon.dtos.responses;

import lombok.Data;


@Data
public class UserLoginResponse {
    private String message;
    private String userId;
    private String role;
}