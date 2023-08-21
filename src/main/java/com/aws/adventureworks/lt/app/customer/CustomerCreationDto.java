package com.aws.adventureworks.lt.app.customer;

public record CustomerCreationDto(String title,  String firstName,
                                  String middleName,  String lastName,
                                  String suffix,  String modifiedDate,
                                  String guid,  String password) {

    public CustomerCreationDto(Customer customer){
        this(customer.getTitle(), customer.getFirstName(),
                customer.getMiddleName(), customer.getLastName(),
                customer.getSuffix(), customer.getModifiedDate(),
                customer.getGuid().toString(), "");
    }
}
