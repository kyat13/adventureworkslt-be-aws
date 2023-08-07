package com.aws.adventureworks.lt.app.customer;

import lombok.*;

@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class CustomerDto {
    String customerId;
    String title;
    String firstName;
    String middleName;
    String lastName;
    String suffix;

    public CustomerDto(Customer customer){
        this.customerId = String.valueOf(customer.getCustomerId());
        this.title = customer.getTitle();
        this.firstName =  customer.getFirstName();
        this.middleName = customer.getMiddleName();
        this.lastName = customer.getLastName();
        this.suffix = customer.getSuffix();
    }
}
