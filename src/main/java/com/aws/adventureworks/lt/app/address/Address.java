package com.aws.adventureworks.lt.app.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Address", schema = "SalesLT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @Column(name="AddressId")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int addressId;
    @Column(name="AddressLine1")
    private String addressLine1;
    @Column(name="AddressLine2")
    private String addressLine2;
    @Column(name="City")
    private String city;
    @Column(name="StateProvince")
    private String stateProvince;
    @Column(name="CountryRegion")
    private String countryRegion;
    @Column(name="PostalCode")
    private String postalCode;
    @GenericGenerator(name = "generator")
    @GeneratedValue(generator = "generator")
    @Column(name = "rowguid", columnDefinition = "uuid")
    private UUID guid;
    @Column(name="ModifiedDate")
    private LocalDateTime modifiedDate;
}
