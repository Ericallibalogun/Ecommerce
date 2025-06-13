package org.africa.semicolon.dtos.responses;

import lombok.Data;
import org.africa.semicolon.data.models.OrderItem;
import org.africa.semicolon.data.models.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PlaceOrderResponse {
    private String message;
    private String orderId;
    private String userId;
    private String customerName;
    private BigDecimal totalAmount;
    private List<OrderItemResponse> items;
    private LocalDateTime orderDate;
    private Status status;
    private String shippingAddress;
}
