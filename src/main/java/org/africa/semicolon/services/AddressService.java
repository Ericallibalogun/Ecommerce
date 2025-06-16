package org.africa.semicolon.services;

import org.africa.semicolon.dtos.requests.AddAddressRequest;
import org.africa.semicolon.dtos.responses.AddAddressResponse;

public interface AddressService {
    AddAddressResponse addAddress(AddAddressRequest request);
}
