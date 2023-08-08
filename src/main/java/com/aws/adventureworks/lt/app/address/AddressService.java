package com.aws.adventureworks.lt.app.address;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {
    private AddressRepository addressRepository;
}
