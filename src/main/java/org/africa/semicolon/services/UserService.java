package org.africa.semicolon.services;

import org.africa.semicolon.dtos.requests.RegisterUserRequest;
import org.africa.semicolon.dtos.requests.UserLoginRequest;
import org.africa.semicolon.dtos.resposes.RegisterUserResponse;
import org.africa.semicolon.dtos.resposes.UserLoginResponse;

public interface UserService {

    RegisterUserResponse register(RegisterUserRequest request);
    UserLoginResponse login(UserLoginRequest request);

}
