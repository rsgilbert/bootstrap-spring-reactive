package com.example.bootstrap;

import com.example.bootstrap.Customer;
import com.example.bootstrap.CustomerService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class BaseCustomerService implements CustomerService {
    private final RowMapper<Customer> rowMapper = (rs, i) -> new Customer(rs.getLong("id"), rs.getString("name"));

    private final JdbcTemplate jdbcTemplate;

    protected BaseCustomerService(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public Collection<Customer> save(String... names) {
        List<Customer> customerList = new ArrayList<>();
        for(String name: names) {
           Customer customer = this.save(name);
           customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public Customer save(String name) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO CUSTOMERS (name) values (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            return ps;
        }, keyHolder);
        Long keyHolderKey = Objects.requireNonNull(keyHolder.getKey()).longValue();
        Customer customer = this.findById(keyHolderKey);
        Assert.notNull(name, "The name given must not be null");
        return customer;
    }

    @Override
    public Customer findById(Long id) {
        String sql = "SELECT * FROM Customers WHERE id = ?";
        return this.jdbcTemplate.queryForObject(sql, this.rowMapper, id);
    }

    @Override
    public Collection<Customer> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM Customers", rowMapper);
    }
}