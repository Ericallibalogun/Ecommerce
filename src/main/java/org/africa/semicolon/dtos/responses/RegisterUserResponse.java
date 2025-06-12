package org.africa.semicolon.dtos.responses;

import lombok.Data;
import org.africa.semicolon.data.models.Role;

@Data
public class RegisterUserResponse {
    private String id;
    private String name;
    private String message;
    private Role role = Role.CUSTOMER;
}
