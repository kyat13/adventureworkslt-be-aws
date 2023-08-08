package com.aws.adventureworks.lt.app.address;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;
}
