package org.africa.semicolon.services;

import org.africa.semicolon.data.models.User;
import org.africa.semicolon.data.repositories.UserRepo;
import org.africa.semicolon.dtos.requests.RegisterUserRequest;
import org.africa.semicolon.dtos.requests.UserLoginRequest;
import org.africa.semicolon.dtos.responses.RegisterUserResponse;
import org.africa.semicolon.dtos.responses.UserLoginResponse;
import org.africa.semicolon.exceptions.EmailNotfoundException;
import org.africa.semicolon.exceptions.InvalidPasswordException;
import org.africa.semicolon.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public RegisterUserResponse register(RegisterUserRequest request) {
        User user = Mapper.toUser(request);
        userRepo.save(user);
        return Mapper.toRegisterResponse(user);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        UserLoginResponse response = new UserLoginResponse();
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new EmailNotfoundException("Invalid email or password"));
        if (!user.getPassword().equals(request.getPassword())) {
            throw new InvalidPasswordException("Invalid email or email");
        }
        response.setMessage("Login successful. Welcome " + user.getName() + "!");
        response.setUserId(user.getId());
        response.setRole(user.getRole());

        return response;
    }
}
