package org.africa.semicolon.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class PlaceOrderResponse {
    private String orderId;
    private String message;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
}
