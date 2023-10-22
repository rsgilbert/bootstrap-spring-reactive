package com.example.bootstrap;

import java.util.Collection;

public interface CustomerService  {
    Collection<Customer> save(String... names);
    Customer save(String name);
    Customer findById(Long id);
    Collection<Customer> findAll();
}