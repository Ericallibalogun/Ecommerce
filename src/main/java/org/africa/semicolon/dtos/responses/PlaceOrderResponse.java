package org.africa.semicolon.dtos.responses;

import lombok.Data;
import org.africa.semicolon.data.models.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class PlaceOrderResponse {
    private String orderId;
    private String message;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private Status status;
}
