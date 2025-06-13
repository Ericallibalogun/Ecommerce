package org.africa.semicolon.dtos.requests;

import lombok.Data;
import org.africa.semicolon.data.models.OrderItem;

import java.util.List;

@Data
public class PlaceOrderRequest {
    private String userId;
    private List<OrderItem> items;
    private String shippingAddressId;
}
