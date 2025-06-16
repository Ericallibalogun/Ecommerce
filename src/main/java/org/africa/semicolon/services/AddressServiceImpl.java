package org.africa.semicolon.services;

import lombok.RequiredArgsConstructor;
import org.africa.semicolon.data.models.Address;
import org.africa.semicolon.data.repositories.AddressRepo;
import org.africa.semicolon.dtos.requests.AddAddressRequest;
import org.africa.semicolon.dtos.responses.AddAddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepo addressRepo;
    @Override
    public AddAddressResponse addAddress(AddAddressRequest request) {
        Address address = new Address();
        address.setUserId(request.getUserId());
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setCountry(request.getCountry());
        address.setPostalCode(request.getPostalCode());

        Address saved = addressRepo.save(address);

        AddAddressResponse response = new AddAddressResponse();
        response.setAddressId(saved.getId());
        response.setMessage("Address added successfully");
        return response;
    }
}
