package com.aws.adventureworks.lt.app.seeder;

import com.aws.adventureworks.lt.app.customer.Customer;
import com.aws.adventureworks.lt.app.customer.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.util.List;
import java.util.UUID;

@Configuration
@AllArgsConstructor
//@Profile("h2")
@Slf4j
public class Dataseeder {

    @Autowired
    private final CustomerRepository customerRepository;

    //todo: combine
    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedQuestions();
    }

    private void seedQuestions() {
        Customer q1 = new Customer(1, "mr", "John", "M", "Travolta", "", "2005-08-01 00:00:00.000", UUID.randomUUID(), "h45h", "541T");
        Customer q2 = new Customer(2, "mr", "Jack", "T", "Sparrow", "Jr.", "2005-08-01 00:00:00.000", UUID.randomUUID(), "h45h", "541T");
        Customer q3 = new Customer(3, "ms", "Jane", "", "Austen", "", "2005-08-01 00:00:00.000", UUID.randomUUID(), "h45h", "541T");


        customerRepository.saveAll(List.of(q1,q2,q3));
    }
}
