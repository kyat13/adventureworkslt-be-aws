package com.aws.adventureworks.lt.app.customer;

import com.aws.adventureworks.lt.app.utils.PasswordHandler;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public Page<CustomerDto> getAllCustomers(Pageable pageable) {
        Logger logger = LoggerFactory.getLogger(CustomerService.class);
        logger.info("getAllCustomers:::Pageable: " + pageable);
        Page<CustomerDto> results = customerRepository.findAll(pageable).map(CustomerDto::new);
        logger.info("getAllCustomers:::results size: " + results.getContent().size());
        logger.info("getAllCustomers:::results: " + results.getTotalElements());
        return results;
    }

    public CustomerDto addCustomer(CustomerCreationDto customerDto) {
        Customer newCustomer = new Customer(
                0,
//                null,
                customerDto.getTitle(),
                customerDto.getFirstName(),
                customerDto.getMiddleName(),
                customerDto.getLastName(),
                customerDto.getSuffix(),
                customerDto.getModifiedDate(),
                UUID.randomUUID(), PasswordHandler.generatePasswordHash(customerDto), "TkEK");

        Customer savedCustomer = customerRepository.save(newCustomer);
        return new CustomerDto(savedCustomer);
    }
}
