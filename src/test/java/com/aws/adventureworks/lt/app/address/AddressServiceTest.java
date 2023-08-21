package com.aws.adventureworks.lt.app.address;


import com.aws.adventureworks.lt.app.customer.Customer;
import com.aws.adventureworks.lt.app.customer.CustomerCreationDto;
import com.aws.adventureworks.lt.app.customer.CustomerDto;
import org.assertj.core.api.AssertionsForClassTypes;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {
    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;


    @Test
    void getAllAddress_givenListOf3Addresses_shouldReturnListOf3AddressDto(){
        // arrange
        Address a1 = new Address(1, "32 Street", "District 3", "Pewter City", "Kanto", "Kanto", "111111",UUID.randomUUID(), LocalDateTime.now());
        Address a2 = new Address(2, "22 Street", "District 4", "Cerulean City", "Kanto", "Kanto", "222222",UUID.randomUUID(), LocalDateTime.now());
        Address a3 = new Address(3, "64 Street", "District 5", "Indigo City", "Kanto", "Kanto", "333333",UUID.randomUUID(), LocalDateTime.now());

        Pageable pageable = PageRequest.of(0, 25);
        Mockito.when(addressRepository.findAll(pageable)).thenReturn( new PageImpl<>(List.of(a1, a2, a3)));

        List<AddressDto> expectedResults = List.of(new AddressDto(a1),
                new AddressDto(a2),new AddressDto(a3) );

        // act
        Page<AddressDto> source = addressService.getAllAddresses(pageable);

        // assert
        assertThat(source.getContent()).isEqualTo(expectedResults);

    }
    @Test
    void getAddress_givenId_shouldReturnAddressDto(){
        // arrange
        Address a1 = new Address(1, "32 Street", "District 3", "Pewter City", "Kanto", "Kanto", "111111",UUID.randomUUID(), LocalDateTime.now());
        Mockito.when(addressRepository.findById(a1.getAddressId())).thenReturn(Optional.of(a1));

        AddressDto expectedResult = new AddressDto(a1);

        // act
        AddressDto result = addressService.getAddress(1);

        // assert
        AssertionsForClassTypes.assertThat(result).isEqualTo(expectedResult);

    }


    @Test
    void addAddress_givenAddressDto_shouldReturnAddressDto(){
        // arrange
        Address a1 = new Address(1, "32 Street", "District 3", "Pewter City", "Kanto", "Kanto", "111111",UUID.randomUUID(), LocalDateTime.now());
        Mockito.when(addressRepository.save(any(Address.class))).thenReturn(a1);
        AddressDto addressDto = new AddressDto(a1);

        // act
        AddressDto source = addressService.addAddress(addressDto);

        // assert
        AssertionsForClassTypes.assertThat(source).isEqualTo(addressDto);

    }
}
