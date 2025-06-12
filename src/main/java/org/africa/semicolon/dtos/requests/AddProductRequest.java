package org.africa.semicolon.dtos.requests;

import lombok.Data;
import org.africa.semicolon.data.models.Category;

import java.math.BigDecimal;

@Data
public class AddProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private int quantityAvailable;
    private String categoryId;
    private String imageUrl;


}
