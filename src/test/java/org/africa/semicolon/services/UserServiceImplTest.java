package org.africa.semicolon.services;

import org.africa.semicolon.data.models.*;
import org.africa.semicolon.data.repositories.CategoryRepo;
import org.africa.semicolon.data.repositories.OrderRepo;
import org.africa.semicolon.data.repositories.ProductRepo;
import org.africa.semicolon.data.repositories.UserRepo;
import org.africa.semicolon.dtos.requests.AddProductRequest;
import org.africa.semicolon.dtos.requests.RegisterUserRequest;
import org.africa.semicolon.dtos.requests.UpdateProductRequest;
import org.africa.semicolon.dtos.requests.UserLoginRequest;
import org.africa.semicolon.dtos.responses.*;
import org.africa.semicolon.exceptions.EmailNotfoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private ProductRepo productRepo;

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private OrderRepo orderRepo;

    @InjectMocks
    private UserServiceImpl userService;
    @InjectMocks
    private ProductServiceImpl productService;
    @InjectMocks
    private OrderServiceImpl orderService;

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
        assertEquals(Role.CUSTOMER,response.getRole());

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
    @Test
    public void testAdminCanLogin(){
        UserLoginRequest request = new  UserLoginRequest();
        request.setEmail("admin@gmail.com");
        request.setPassword("admin");

        User user = new User();
        user.setName("Eric alli");
        user.setEmail("admin@gmail.com");
        user.setPassword("admin");
        user.setRole(Role.ADMIN);

        when(userRepo.findByEmail("admin@gmail.com")).thenReturn(Optional.of(user));

        UserLoginResponse response = userService.login(request);

        assertNotNull(response);
        assertEquals("Login successful. Welcome Eric alli!",response.getMessage());
        assertEquals(Role.ADMIN,response.getRole());

        verify(userRepo, times(1)).findByEmail("admin@gmail.com");
    }
    @Test
    public void testThatProductCanBeAdded(){
        AddProductRequest request = new AddProductRequest();
        request.setName("led tv");
        request.setImageUrl("image.jpg");
        request.setPrice(BigDecimal.valueOf(250));
        request.setQuantityAvailable(23);
        request.setDescription("strong and durable");
        request.setCategoryId("cat23");

        Category category = new Category();
        category.setId("cat23");
        category.setName("Electronics");

        when(productRepo.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(categoryRepo.findById("cat23")).thenReturn(Optional.of(category));

        AddProductResponse response = productService.addProduct(request);
        assertNotNull(response);
        assertEquals("Product added successfully",response.getMessage());
        assertEquals("led tv",response.getName());
        assertEquals("image.jpg",response.getImageUrl());

        verify(productRepo, times(1)).save(any(Product.class));
        verify(categoryRepo).findById("cat23");
    }

    @Test
    public void testProductCanBeUpdated(){
        UpdateProductRequest request = new UpdateProductRequest();
        request.setProductId("11");
        request.setName("Updated Laptop");
        request.setDescription("Updated description");
        request.setPrice(BigDecimal.valueOf(950));
        request.setCategoryId("cat123");

        Product existingProduct = new Product();
        existingProduct.setId("11");
        existingProduct.setName("Old Laptop");
        existingProduct.setDescription("Old description");
        existingProduct.setPrice(BigDecimal.valueOf(900));

        Category category = new Category();
        category.setId("cat123");
        category.setName("Electronics");

        when(productRepo.findById("11")).thenReturn(Optional.of(existingProduct));
        when(productRepo.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(categoryRepo.findById("cat123")).thenReturn(Optional.of(category));

        UpdateProductResponse response = productService.updateProduct(request);

        assertNotNull(response);
        assertEquals("Updated Laptop", response.getName());
        assertEquals("Electronics", response.getCategoryName());
        assertEquals(BigDecimal.valueOf(950), response.getPrice());

        verify(productRepo).findById("11");
        verify(productRepo).save(any(Product.class));
        verify(categoryRepo).findById("cat123");
    }

    @Test
    public void testDeleteProductWhenExists() {
        String productId = "21";
        Product product = new Product();
        product.setId(productId);
        product.setName("Old Laptop");

        when(productRepo.findById(productId)).thenReturn(Optional.of(product));

        productService.deleteProduct(productId);

        verify(productRepo, times(1)).findById("21");
        verify(productRepo, times(1)).deleteById("21");
    }

    @Test
    public void testGetOrderById() {
        OrderItem item1 = new OrderItem();
        item1.setProductId("pro22");
        item1.setProductName("Bag");
        item1.setQuantity(2);
        item1.setPrice(BigDecimal.valueOf(100));
        item1.setTotalPrice(BigDecimal.valueOf(200));

        List<OrderItem> items = List.of(item1);


        Address address = new Address();
        address.setStreet("123 Ajegunle St");
        address.setCity("Lagos");

        Order order = new Order();
        order.setId("order123");
        order.setUserId("user456");
        order.setItems(items);
        order.setTotalAmount(BigDecimal.valueOf(200));
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.PENDING);
        order.setShippingAddress(address);

        when(orderRepo.findById("order123")).thenReturn(Optional.of(order));

        PlaceOrderResponse response = orderService.getOrderById("order123");

        assertNotNull(response);
        assertEquals("order123", response.getOrderId());
        assertEquals("user456", response.getUserId());
        assertEquals(1, response.getItems().size());
        assertEquals("Bag", response.getItems().getFirst().getProductName());
        assertEquals("123 Ajegunle St, Lagos", response.getShippingAddress());

        verify(orderRepo, times(1)).findById("order123");
    }

}
