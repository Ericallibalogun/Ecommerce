package org.africa.semicolon.dtos.requests;

import lombok.Data;

@Data
public class PlaceOrderRequest {
    private String userId;
    private String productId;
    private int quantity;
    private String addressId;
}
