package com.aws.adventureworks.lt.app.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void GET_customers_shouldReturnPageOfCustomerDto() throws Exception {
        // arrange
        Customer q1 = new Customer(1, "mr", "John", "M", "Travolta", "", "2005-08-01 00:00:00.000", UUID.randomUUID(), "h45h", "541T");
        Customer q2 = new Customer(2, "mr", "Jack", "T", "Sparrow", "Jr.", "2005-08-01 00:00:00.000", UUID.randomUUID(), "h45h", "541T");
        Customer q3 = new Customer(3, "ms", "Jane", "", "Austen", "", "2005-08-01 00:00:00.000", UUID.randomUUID(), "h45h", "541T");

        Pageable pageable = PageRequest.of(0, 25);
        Page<CustomerDto> expectedResponse = new PageImpl<>(Stream.of(q1,q2,q3).map(CustomerDto::new).collect(Collectors.toList()));

        Mockito.when(customerService.getAllCustomers(pageable)).thenReturn(
                expectedResponse);

        // act
        // assert
        this.mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
    }

    @Test
    void POST_customer_shouldReturnCustomerDto() throws Exception {
        // arrange
        Customer customer1 = new Customer(1, "mr", "John", "M", "Travolta", "", "2005-08-01 00:00:00.000",  UUID.randomUUID(), "h45h", "TkEK");
        CustomerCreationDto customerDto = new CustomerCreationDto(customer1);
        CustomerDto responseDto = new CustomerDto(customer1);

        Mockito.when(customerService.addCustomer(customerDto)).thenReturn(
                responseDto);

        // act
        // assert
        this.mockMvc.perform(
                        post("/api/customer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(customerDto))
                ).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }
}
