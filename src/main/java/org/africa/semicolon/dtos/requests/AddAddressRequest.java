package org.africa.semicolon.dtos.requests;

import lombok.Data;

@Data
public class AddAddressRequest {
    private String userId;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
}
