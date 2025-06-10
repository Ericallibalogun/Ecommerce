package org.africa.semicolon.services;

import org.africa.semicolon.dtos.requests.RegisterUserRequest;
import org.africa.semicolon.dtos.resposes.RegisterUserResponse;

public interface UserService {

    RegisterUserResponse register(RegisterUserRequest request);

}
