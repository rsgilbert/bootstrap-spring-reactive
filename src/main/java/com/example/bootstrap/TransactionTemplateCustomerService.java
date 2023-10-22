package com.example.bootstrap;

import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Collection;

public class TransactionTemplateCustomerService extends BaseCustomerService {
    private final TransactionTemplate transactionTemplate;
    public TransactionTemplateCustomerService(DataSource dataSource, TransactionTemplate transactionTemplate) {
        super(dataSource);
        this.transactionTemplate = transactionTemplate;
    }


    @Override
    public Collection<Customer> save(String... names) {
        return this.transactionTemplate.execute(s -> super.save(names));
    }

    @Override
    public  Collection<Customer> findAll() {
        return this.transactionTemplate.execute(s -> super.findAll());
    }
}
