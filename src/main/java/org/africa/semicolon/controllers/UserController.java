package org.africa.semicolon.controllers;

import org.africa.semicolon.dtos.requests.AddAddressRequest;
import org.africa.semicolon.dtos.requests.RegisterUserRequest;
import org.africa.semicolon.dtos.requests.UserLoginRequest;
import org.africa.semicolon.dtos.responses.AddAddressResponse;
import org.africa.semicolon.dtos.responses.ApiResponse;
import org.africa.semicolon.dtos.responses.RegisterUserResponse;
import org.africa.semicolon.dtos.responses.UserLoginResponse;
import org.africa.semicolon.services.AddressService;
import org.africa.semicolon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private  UserService userService;
    @Autowired
    private AddressService addressService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest request){
        try{
           RegisterUserResponse response = userService.register(request);
           return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request){
        try{
                    UserLoginResponse response = userService.login(request);
            return new ResponseEntity<>(new ApiResponse(true,response),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/addAddress")
    public ResponseEntity<?> addAddress(@RequestBody AddAddressRequest request) {
        try {
            AddAddressResponse response = addressService.addAddress(request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
