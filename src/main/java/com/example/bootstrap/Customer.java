package com.example.bootstrap;


import lombok.Data;

@Data
public class Customer {
    private long id;

    private String name;

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

}