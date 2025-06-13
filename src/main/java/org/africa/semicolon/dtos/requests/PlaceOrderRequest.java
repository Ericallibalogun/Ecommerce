package org.africa.semicolon.dtos.requests;

import lombok.Data;
import org.africa.semicolon.data.models.OrderItem;
import org.africa.semicolon.dtos.responses.OrderItemResponse;

import java.util.List;

@Data
public class PlaceOrderRequest {
    private String userId;
    private List<OrderItemResponse> items;
    private String shippingAddressId;
}
