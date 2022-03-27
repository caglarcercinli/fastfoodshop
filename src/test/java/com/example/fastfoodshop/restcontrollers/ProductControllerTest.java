package com.example.fastfoodshop.restcontrollers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/insertProduct.sql")
public class ProductControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final MockMvc mvc;

    public ProductControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    private long idFromTestProduct() {
        return jdbcTemplate.queryForObject("SELECT id FROM products WHERE name='test'", Long.class);
    }

    @Test
    void nonExistingProductReading() throws Exception {
        mvc.perform(get("/products/{id}", -1))
                .andExpect(status().isNotFound());
    }

    @Test
    void existingProductReading() throws Exception {
        var id = idFromTestProduct();
        mvc.perform(get("/products/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id));
    }

    @Test
    void allProductsReading() throws Exception {
        mvc.perform(get("/products"))
                .andExpectAll(status().isBadRequest());
    }
}
