package com.aws.adventureworks.lt.app.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/api/customers")
    public Page<CustomerDto> getAllCustomers(@RequestParam(defaultValue = "0", required = false) int page,
                                             @RequestParam(defaultValue = "25", required = false) int size){
        return customerService.getAllCustomers(PageRequest.of(page, size));
    }

    @PostMapping("/api/customer")
    public CustomerDto addNewCustomer(@RequestBody CustomerCreationDto newCustomer){
        return customerService.addCustomer(newCustomer);
    }
}
