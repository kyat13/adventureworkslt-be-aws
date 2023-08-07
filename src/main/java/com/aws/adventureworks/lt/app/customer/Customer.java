package com.aws.adventureworks.lt.app.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "Customer", schema = "SalesLT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name="CustomerID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int customerId;
    @Column(name = "Title")
    private String title;
    @Column(name="firstname")
    private String firstName;
    @Column(name = "middlename")
    private String middleName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "Suffix")
    private String suffix;
    @Column(name = "modifieddate")
    private String modifiedDate;
    @GenericGenerator(name = "generator")
    @GeneratedValue(generator = "generator")
    @Column(name = "rowguid", columnDefinition = "uuid")
    private UUID guid;
    @Column(name ="passwordhash")
    private String passwordHash;
    @Column(name ="passwordsalt")
    private String passwordSalt;
}
