package com.aws.adventureworks.lt.app.customer;

public record CustomerDto (String customerId, String title,
        String firstName, String middleName,
        String lastName, String suffix) {

    public CustomerDto(Customer customer){
        this(String.valueOf(customer.getCustomerId()), customer.getTitle(),
                customer.getFirstName(), customer.getMiddleName(),
                customer.getLastName(), customer.getSuffix());
    }
}
