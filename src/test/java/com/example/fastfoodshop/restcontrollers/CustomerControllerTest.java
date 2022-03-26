package com.example.fastfoodshop.restcontrollers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/insertCustomer.sql")
public class CustomerControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final MockMvc mvc;

    public CustomerControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    private long idFromTestCustomer() {
        return jdbcTemplate.queryForObject("SELECT id FROM customers WHERE name='test'", Long.class);
    }

    @Test
    void nonExistingCustomerReading() throws Exception {
        mvc.perform(get("/customers/{id}", -1))
                .andExpect(status().isNotFound());
    }

    @Test
    void existingCustomerReading() throws Exception {
        var id = idFromTestCustomer();
        mvc.perform(get("/customers/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id));
    }

    @Test
    void allCustomersReading() throws Exception {
        mvc.perform(get("/customers"))
                .andExpectAll(status().isOk());
    }
}
