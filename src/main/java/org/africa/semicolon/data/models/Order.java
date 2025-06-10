package org.africa.semicolon.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("collection = orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    private String Id;
    private String userId;
    private List<OrderItem> items;
    private double totalAmount;
    private String status;
    private LocalDateTime orderDate;
    private Address shippingAddress;
}
