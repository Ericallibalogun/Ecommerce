package org.africa.semicolon.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class AddProductResponse {
    private String message;
    private String productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private String categoryId;
    private String categoryName;
    private String imageUrl;

}
