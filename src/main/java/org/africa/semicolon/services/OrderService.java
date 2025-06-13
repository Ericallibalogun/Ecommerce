package org.africa.semicolon.services;

import org.africa.semicolon.dtos.requests.PlaceOrderRequest;
import org.africa.semicolon.dtos.responses.PlaceOrderResponse;

public interface OrderService {
    PlaceOrderResponse placeOrder(PlaceOrderRequest request);
    PlaceOrderResponse getOrderById(String id);
}
