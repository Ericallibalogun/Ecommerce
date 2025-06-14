package org.africa.semicolon.dtos.requests;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductSearchRequest {
    private String keyword;
    private String categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer minQuantity;
}
