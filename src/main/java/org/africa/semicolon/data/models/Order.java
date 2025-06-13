package org.africa.semicolon.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document("collection = orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String Id;
    private String productId;
    private String userId;
    private int quantity;
    private List<OrderItem> items;
    private BigDecimal totalAmount;
    private Status status;
    private LocalDateTime orderDate;
    private Address shippingAddress;
}
