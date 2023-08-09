package com.aws.adventureworks.lt.app.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void getAllCustomers_givenListOf3Customers_shouldReturnListOf3CustomerDto(){
        // arrange
        Customer customer1 = new Customer(1, "mr", "John", "M", "Travolta", "", "2005-08-01 00:00:00.000", UUID.randomUUID(), "h45h", "541T");
        Customer customer2 = new Customer(2, "mr", "Jack", "T", "Sparrow", "Jr.", "2005-08-01 00:00:00.000", UUID.randomUUID(), "h45h", "541T");
        Customer customer3 = new Customer(3, "ms", "Jane", "", "Austen", "", "2005-08-01 00:00:00.000", UUID.randomUUID(), "h45h", "541T");

        Pageable pageable = PageRequest.of(0, 25);
        Mockito.when(customerRepository.findAll(pageable)).thenReturn( new PageImpl<>(List.of(customer1, customer2, customer3)));

        List<CustomerDto> expectedResults = List.of(new CustomerDto(customer1),
                new CustomerDto(customer2),new CustomerDto(customer3) );

        // act
        Page<CustomerDto> source = customerService.getAllCustomers(pageable);

        // assert
        assertThat(source.getContent()).isEqualTo(expectedResults);

    }
    @Test
    void getCustomer_givenCustomer_shouldReturnCustomerDto(){
        // arrange
        Customer customer1 = new Customer(1, "mr", "John", "M", "Travolta", "", "2005-08-01 00:00:00.000", UUID.randomUUID(), "h45h", "541T");
        Mockito.when(customerRepository.findById(customer1.getCustomerId())).thenReturn(Optional.of(customer1));

        CustomerDto expectedResult = new CustomerDto(customer1);

        // act
        CustomerDto result = customerService.getCustomer(1);

        // assert
        assertThat(result).isEqualTo(expectedResult);

    }


    @Test
    void addCustomer_givenCustomerCreationDto_shouldReturnCustomerDto(){
        // arrange
        CustomerCreationDto newCustomerDto = new CustomerCreationDto( "mr", "John", "M", "Travolta", "", "2005-08-01 00:00:00.000" , "guid-123456", "password");

        Customer customer1 = new Customer(1, "mr", "John", "M", "Travolta", "", "2005-08-01 00:00:00.000" , UUID.randomUUID(), "hashedPassword", "TkEK");
        CustomerDto savedCustomer = new CustomerDto(customer1);
        Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(customer1);

        // act
        CustomerDto source = customerService.addCustomer(newCustomerDto);

        // assert
        assertThat(source).isEqualTo(savedCustomer);

    }
}
