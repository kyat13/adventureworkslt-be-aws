package com.aws.adventureworks.lt.app.address;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {
    private AddressRepository addressRepository;

    public Page<AddressDto> getAllAddresses(Pageable pageable) {
        return addressRepository.findAll(pageable).map(AddressDto::new);
    }

    public AddressDto getAddress(int id) {
        return new AddressDto(addressRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public AddressDto addAddress(AddressDto addressDto) {
        Address newAddress = new Address(
                addressDto.addressId(), addressDto.addressLine1(),
                addressDto.addressLine2(), addressDto.city(),
                addressDto.stateProvince(), addressDto.countryRegion(),
                addressDto.postalCode(), addressDto.guid(),
                addressDto.modifiedDate());
        return new AddressDto(addressRepository.save(newAddress));
    }
}
