package com.aws.adventureworks.lt.app.customer;

import com.aws.adventureworks.lt.app.utils.PasswordHandler;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public Page<CustomerDto> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable).map(CustomerDto::new);
    }

    public CustomerDto addCustomer(CustomerCreationDto customerDto) {
        Customer newCustomer = new Customer(
                -1,
                customerDto.title(),
                customerDto.firstName(),
                customerDto.middleName(),
                customerDto.lastName(),
                customerDto.suffix(),
                customerDto.modifiedDate(),
                UUID.randomUUID(), PasswordHandler.generatePasswordHash(customerDto), "TkEK");

        return new CustomerDto(customerRepository.save(newCustomer));
    }
}
