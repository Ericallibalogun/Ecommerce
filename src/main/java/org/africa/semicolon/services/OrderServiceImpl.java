package org.africa.semicolon.services;

import lombok.RequiredArgsConstructor;
import org.africa.semicolon.data.models.Order;
import org.africa.semicolon.data.models.Product;
import org.africa.semicolon.data.models.Status;
import org.africa.semicolon.data.models.User;
import org.africa.semicolon.data.repositories.OrderRepo;
import org.africa.semicolon.data.repositories.ProductRepo;
import org.africa.semicolon.data.repositories.UserRepo;
import org.africa.semicolon.dtos.requests.PlaceOrderRequest;
import org.africa.semicolon.dtos.responses.PlaceOrderResponse;
import org.africa.semicolon.exceptions.InsufficientStockException;
import org.africa.semicolon.exceptions.ProductNotFoundException;
import org.africa.semicolon.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;


    @Override
    public PlaceOrderResponse placeOrder(PlaceOrderRequest request) {
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        if(product.getQuantity() < request.getQuantity()) throw new InsufficientStockException("Not enough stock available");

        product.setQuantity(product.getQuantity() - request.getQuantity());
        productRepo.save(product);

        Order order = new Order();
        order.setUserId(user.getId());
        order.setProductId(product.getId());
        order.setQuantity(request.getQuantity());
        order.setTotalAmount(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
        order.setStatus(Status.PLACED);
        order.setOrderDate(LocalDateTime.now());

        Order savedOrder = orderRepo.save(order);
    }
}
