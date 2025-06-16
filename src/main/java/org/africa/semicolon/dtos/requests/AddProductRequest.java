package org.africa.semicolon.dtos.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private String categoryId;
    private String imageUrl;


}
