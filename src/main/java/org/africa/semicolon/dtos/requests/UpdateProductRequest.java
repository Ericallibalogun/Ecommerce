package org.africa.semicolon.dtos.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    private String productId;
    private String name;
    private String description;
    private BigDecimal price;
    private String categoryId;
}