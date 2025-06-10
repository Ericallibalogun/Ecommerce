package org.africa.semicolon.services;

import org.africa.semicolon.data.models.User;
import org.africa.semicolon.data.repositories.UserRepo;
import org.africa.semicolon.dtos.requests.RegisterUserRequest;
import org.africa.semicolon.dtos.requests.UserLoginRequest;
import org.africa.semicolon.dtos.resposes.RegisterUserResponse;
import org.africa.semicolon.dtos.resposes.UserLoginResponse;
import org.africa.semicolon.exceptions.EmailNotfoundException;
import org.africa.semicolon.exceptions.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public RegisterUserResponse register(RegisterUserRequest request) {
        RegisterUserResponse response = new RegisterUserResponse();
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());

        userRepo.save(user);

        response.setName(user.getName());
        response.setMessage("Registered successfully...Welcome "+ user.getName());

        return response;
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new EmailNotfoundException("Invalid email or password"));
        if (!user.getPassword().equals(request.getPassword())) {
            throw new InvalidPasswordException("Invalid email or email");
        }
        return UserLoginResponse.builder()
                .message("Welcome back, " + user.getName())
                .userId(user.getId())
                .role(user.getRole().name())
                .build();
    }
}
