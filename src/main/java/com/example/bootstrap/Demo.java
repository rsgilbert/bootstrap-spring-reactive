package com.example.bootstrap;

import lombok.extern.log4j.Log4j2;
import org.springframework.util.Assert;

import java.util.stream.Stream;

@Log4j2
public class Demo {
    public static void workWithCustomerService(Class<?> label, CustomerService customerService) {
       log.info("====================");
       log.info(label.getName());
       log.info("==================");

        Stream.of("John", "James", "Peter", "Simon").map(customerService::save)
            .forEach(customer -> log.info("Saved " + customer.getName()));

        customerService.findAll().forEach(customer -> {
            Long customerId = customer.getId();
            Customer customerById = customerService.findById(customerId);
            log.info("Found: " + customerById);
            Assert.notNull(customerById, "The found customer must not be null");
            Assert.isTrue(customerById.equals(customer), "The customer found must be the expected one");

        });
    }
}
