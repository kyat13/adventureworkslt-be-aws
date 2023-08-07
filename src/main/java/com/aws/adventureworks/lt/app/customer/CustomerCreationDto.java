package com.aws.adventureworks.lt.app.customer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CustomerCreationDto {
    String title;
    String firstName;
    String middleName;
    String lastName;
    String suffix;
    String modifiedDate;
    String guid;
    String password;

    public CustomerCreationDto(Customer customer){
        this.title = customer.getTitle();
        this.firstName =  customer.getFirstName();
        this.middleName = customer.getMiddleName();
        this.lastName = customer.getLastName();
        this.suffix = customer.getSuffix();
        this.modifiedDate = customer.getModifiedDate();
        this.guid = customer.getGuid().toString();
        this.password = "";
    }
}
