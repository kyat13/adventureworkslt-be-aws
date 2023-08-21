package com.aws.adventureworks.lt.app.address;

import java.time.LocalDateTime;
import java.util.UUID;

public record AddressDto(int addressId, String addressLine1,
                         String addressLine2, String city,
                         String stateProvince, String countryRegion,
                         String postalCode, UUID guid,
                         LocalDateTime modifiedDate) {
    public AddressDto(Address address){
        this(address.getAddressId(), address.getAddressLine1(),
                address.getAddressLine2(), address.getCity(),
                address.getStateProvince(), address.getCountryRegion(),
                address.getPostalCode(), address.getGuid(),
                address.getModifiedDate());
    }

}
