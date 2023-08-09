package com.aws.adventureworks.lt.app.address;

import com.aws.adventureworks.lt.app.customer.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
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

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AddressController.class)
class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    class GET {
        @Test
        void address_shouldReturnPageOfAddressDto() throws Exception {
            // arrange
            Address a1 = new Address(1, "32 Street", "District 3", "Pewter City", "Kanto", "Kanto", "111111",UUID.randomUUID(), LocalDateTime.now());
            Address a2 = new Address(2, "22 Street", "District 4", "Cerulean City", "Kanto", "Kanto", "222222",UUID.randomUUID(), LocalDateTime.now());
            Address a3 = new Address(3, "64 Street", "District 5", "Indigo City", "Kanto", "Kanto", "333333",UUID.randomUUID(), LocalDateTime.now());

            Pageable pageable = PageRequest.of(0, 25);
            Page<AddressDto> expectedResponse = new PageImpl<>(Stream.of(a1,a2,a3).map(AddressDto::new).collect(Collectors.toList()));

            Mockito.when(addressService.getAllAddresses(pageable)).thenReturn(
                    expectedResponse);

            // act
            // assert
            mockMvc.perform(get("/api/addresses"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
        }

        @Test
        void address_id_shouldReturnAddressDto() throws Exception {
            // arrange
            Address a1 = new Address(1, "32 Street", "District 3", "Pewter City", "Kanto", "Kanto", "111111",UUID.randomUUID(), LocalDateTime.now());
            AddressDto expectedResponse = new AddressDto(a1);

            Mockito.when(addressService.getAddress(a1.getAddressId())).thenReturn(
                    expectedResponse);

            // act
            // assert
            mockMvc.perform(get("/api/address/" + a1.getAddressId()))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
        }
    }

    @Test
    void POST_address_shouldReturnAddressDto() throws Exception {
        // arrange
        Address a1 = new Address(1, "32 Street", "District 3", "Pewter City", "Kanto", "Kanto", "111111",UUID.randomUUID(), LocalDateTime.now());
        AddressDto addressDto = new AddressDto(a1);

        Mockito.when(addressService.addAddress(addressDto)).thenReturn(
                addressDto);

        // act
        // assert
        this.mockMvc.perform(
                        post("/api/address")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(addressDto))
                ).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(addressDto)));
    }
}
