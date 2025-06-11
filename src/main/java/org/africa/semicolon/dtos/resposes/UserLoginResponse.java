package org.africa.semicolon.dtos.resposes;

import lombok.Builder;
import lombok.Data;

@Data
public class UserLoginResponse {
    private String message;
    private String userId;
    private String role;
}