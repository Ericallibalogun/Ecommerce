package org.africa.semicolon.services;

import org.africa.semicolon.data.models.Role;
import org.africa.semicolon.data.models.User;
import org.africa.semicolon.data.repositories.UserRepo;
import org.africa.semicolon.dtos.requests.RegisterUserRequest;
import org.africa.semicolon.dtos.requests.UserLoginRequest;
import org.africa.semicolon.dtos.resposes.RegisterUserResponse;
import org.africa.semicolon.dtos.resposes.UserLoginResponse;
import org.africa.semicolon.exceptions.EmailNotfoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public  void testRegisterUser() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setName("Eric allibalogun");
        request.setPhone("09082573315");
        request.setEmail("allieric28@gmail.com");
        request.setPassword("123233");


        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());

        when(userRepo.save(any(User.class))).thenReturn(user);
        RegisterUserResponse response = userService.register(request);
        assertNotNull(response);
        assertEquals("Registered successfully...Welcome Eric allibalogun", response.getMessage());

        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    public void testUserCanLogin(){
        UserLoginRequest request = new  UserLoginRequest();
        request.setEmail("allieric28@gmail.com");
        request.setPassword("123233");

        User user = new User();
        user.setName("Eric alli");
        user.setEmail("allieric28@gmail.com");
        user.setPassword("123233");
        user.setPhone("07027242523");
        user.setRole(Role.CUSTOMER);

        when(userRepo.findByEmail("allieric28@gmail.com")).thenReturn(Optional.of(user));

        UserLoginResponse response = userService.login(request);

        assertNotNull(response);
        assertEquals("Login successful. Welcome Eric alli!",response.getMessage());
        assertEquals("CUSTOMER",response.getRole());

        verify(userRepo, times(1)).findByEmail("allieric28@gmail.com");
    }
    @Test
    public void testLogin_InvalidEmail_ThrowsException(){
        UserLoginRequest request = new  UserLoginRequest();
        request.setEmail("allieric71mail.com");
        request.setPassword("password");

        when(userRepo.findByEmail("allieric71@gmail.com")).thenReturn(Optional.empty());

        assertThrows(EmailNotfoundException.class,() -> userService.login(request));
    }


}
