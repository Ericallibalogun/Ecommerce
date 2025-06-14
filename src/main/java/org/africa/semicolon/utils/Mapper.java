package org.africa.semicolon.utils;

import org.africa.semicolon.data.models.*;
import org.africa.semicolon.dtos.requests.AddProductRequest;
import org.africa.semicolon.dtos.requests.PlaceOrderRequest;
import org.africa.semicolon.dtos.requests.RegisterUserRequest;
import org.africa.semicolon.dtos.requests.UpdateProductRequest;
import org.africa.semicolon.dtos.responses.AddProductResponse;
import org.africa.semicolon.dtos.responses.PlaceOrderResponse;
import org.africa.semicolon.dtos.responses.RegisterUserResponse;
import org.africa.semicolon.dtos.responses.UpdateProductResponse;


public class Mapper {

    public static User toUser(RegisterUserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());
        user.setRole(Role.valueOf(Role.CUSTOMER.name()));
        return user;

    }
    public static RegisterUserResponse toRegisterResponse(User user){
        RegisterUserResponse response = new RegisterUserResponse();
        response.setName(user.getName());
        response.setMessage("Registered successfully...Welcome "+ user.getName());
        return response;
    }
    public static Product mapRequestToProduct(AddProductRequest request){
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantityAvailable());
        product.setCategoryId(request.getCategoryId());
        product.setImageUrl(request.getImageUrl());
        return product;
    }
    public static AddProductResponse mapRequestToProductResponse(Product saved,Category category){
        AddProductResponse response = new AddProductResponse();
        response.setMessage("Product added successfully");
        response.setProductId(saved.getId());
        response.setName(saved.getName());
        response.setDescription(saved.getDescription());
        response.setPrice(saved.getPrice());
        response.setImageUrl(saved.getImageUrl());
        response.setCategoryName(category.getName());
        return response;
    }
    public static AddProductResponse mapProductToResponse(Product product, Category category) {
        AddProductResponse response = new AddProductResponse();
        response.setProductId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setCategoryId(product.getCategoryId());


        if(category != null) response.setCategoryName(category.getName());

        return response;

    }
    public static AddProductResponse toAddProductResponse(Product product,Category category){
        AddProductResponse response = new AddProductResponse();
        response.setProductId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setDescription(product.getDescription());
        response.setCategoryName(category.getName());

        return response;
    }
    public static void mapUpdatedProductToResponse(Product product,UpdateProductRequest request){
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setCategoryId(request.getCategoryId());
    }

    public static UpdateProductResponse toUpdateProductResponse(Product product,Category category){
        UpdateProductResponse response = new UpdateProductResponse();
        response.setMessage("Product updated successfully");
        response.setProductId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setDescription(product.getDescription());
        response.setCategoryName(category.getName());
        return response;
    }

    public static AddProductResponse mapProductToAddProductResponse(Product product, String categoryName) {
        AddProductResponse response = new AddProductResponse();
        response.setProductId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setImageUrl(product.getImageUrl());
        response.setCategoryName(categoryName);

        return response;
    }
    public static PlaceOrderResponse mapToPlaceOrderResponse(Order order){
        PlaceOrderResponse response = new PlaceOrderResponse();
        response.setOrderId(order.getId());
        response.setTotalAmount(order.getTotalAmount());
        response.setOrderDate(order.getOrderDate());
        response.setStatus(order.getStatus());
        response.setMessage("Order placed successfully");
        return response;


    }
}
