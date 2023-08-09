package com.aws.adventureworks.lt.app.address;

import com.aws.adventureworks.lt.app.customer.CustomerController;
import com.aws.adventureworks.lt.app.customer.CustomerCreationDto;
import com.aws.adventureworks.lt.app.customer.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/api/addresses")
    public Page<AddressDto> getAllAddresses(@RequestParam(defaultValue = "0", required = false) int page,
                                             @RequestParam(defaultValue = "25", required = false) int size){
        Logger logger = LoggerFactory.getLogger(AddressController.class);
        Page<AddressDto> result = addressService.getAllAddresses(PageRequest.of(page, size));
        logger.info("getAllAddresses:: {}", result);
        return result;
    }

    @GetMapping("/api/address/{id}")
    public AddressDto getAddress(@PathVariable int id){
        return addressService.getAddress(id);
    }

    @PostMapping("/api/address")
    public AddressDto addNewAddress(@RequestBody AddressDto addressDto){
        return addressService.addAddress(addressDto);
    }
}
