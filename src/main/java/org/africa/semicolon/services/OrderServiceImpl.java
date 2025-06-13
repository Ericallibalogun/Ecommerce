package org.africa.semicolon.services;

import lombok.RequiredArgsConstructor;
import org.africa.semicolon.data.models.*;
import org.africa.semicolon.data.repositories.AddressRepo;
import org.africa.semicolon.data.repositories.OrderRepo;
import org.africa.semicolon.data.repositories.ProductRepo;
import org.africa.semicolon.data.repositories.UserRepo;
import org.africa.semicolon.dtos.requests.PlaceOrderRequest;
import org.africa.semicolon.dtos.responses.OrderItemResponse;
import org.africa.semicolon.dtos.responses.PlaceOrderResponse;
import org.africa.semicolon.exceptions.AddressNotFoundException;
import org.africa.semicolon.exceptions.OrderNotFoundException;
import org.africa.semicolon.exceptions.ProductNotFoundException;
import org.africa.semicolon.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AddressRepo addressRepo;



    @Override
    public PlaceOrderResponse placeOrder(PlaceOrderRequest request) {
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Address address = addressRepo.findById(request.getShippingAddressId())
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemResponse itemRequest : request.getItems()) {
            Product product = productRepo.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

            boolean availableQuantityIsInsufficient = product.getQuantity() < itemRequest.getQuantity();
            if (availableQuantityIsInsufficient) throw new RuntimeException("Insufficient stock for " + product.getName());

            OrderItem item = new OrderItem();
            item.setProductId(product.getId());
            item.setProductName(product.getName());
            item.setQuantity(itemRequest.getQuantity());
            item.setPrice(product.getPrice());
            item.setPrice(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
            orderItems.add(item);

            totalAmount = totalAmount.add(item.getTotalPrice());
        }

        Order order = new Order();
        order.setUserId(user.getId());
        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);
        order.setShippingAddress(address);
        order.setStatus(Status.PENDING);
        order.setOrderDate(LocalDateTime.now());

        Order savedOrder = orderRepo.save(order);

        PlaceOrderResponse response = new PlaceOrderResponse();
        response.setOrderId(savedOrder.getId());
        response.setMessage("Order placed successfully");
        response.setTotalAmount(savedOrder.getTotalAmount());
        response.setOrderDate(savedOrder.getOrderDate());
        response.setStatus(savedOrder.getStatus());

        return response;
    }

    @Override
    public PlaceOrderResponse getOrderById(String orderId) {
       Order order = orderRepo.findById(orderId)
               .orElseThrow(() -> new OrderNotFoundException("Order not found"));
       PlaceOrderResponse response = new PlaceOrderResponse();
       response.setOrderId(order.getId());
       response.setUserId(order.getUserId());
       response.setOrderDate(order.getOrderDate());
       response.setStatus(order.getStatus());
       response.setTotalAmount(order.getTotalAmount());

       Address address = order.getShippingAddress();
       response.setShippingAddress(address.getStreet() + ", " + address.getCity());

       List<OrderItemResponse> itemResponses = order.getItems().stream()
               .map(item ->{
                   OrderItemResponse itemResponse = new OrderItemResponse();
                   itemResponse.setProductId(item.getProductId());
                   itemResponse.setProductName(item.getProductName());
                   itemResponse.setQuantity(item.getQuantity());
                   itemResponse.setUnitPrice(item.getPrice());
                   itemResponse.setTotalPrice(item.getTotalPrice());
                   return itemResponse;

               }).collect(Collectors.toList());
       response.setItems(itemResponses);
       return response;
    }
}
