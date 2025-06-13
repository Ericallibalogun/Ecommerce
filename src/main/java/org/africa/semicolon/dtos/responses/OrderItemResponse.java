package org.africa.semicolon.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderItemResponse {
    private String productId;
    private String productName;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}