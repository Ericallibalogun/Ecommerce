package org.africa.semicolon.dtos.responses;

import lombok.Data;
import org.africa.semicolon.data.models.Role;

@Data
public class UserLoginResponse {
    private String message;
    private String userId;
    private String role;
}