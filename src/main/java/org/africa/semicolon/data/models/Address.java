package org.africa.semicolon.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class Address {
    @Id
    private String id;
    private String userId;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
}
