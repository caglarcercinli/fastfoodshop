package com.example.fastfoodshop.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Sql("/insertProduct.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final ProductRepository repository;

    ProductRepositoryTest(ProductRepository repository) {
        this.repository = repository;
    }

    private long idFromTestProduct() {
        return jdbcTemplate.queryForObject("SELECT id FROM products WHERE name='test'", Long.class);
    }

    @Test
    void findById() {
        assertThat(repository.findById(idFromTestProduct()))
                .hasValueSatisfying(product -> assertThat(product.getName()).isEqualTo("test"))
                .hasValueSatisfying(product -> assertThat(product.getPrice()).isEqualTo(BigDecimal.ONE));
    }
}
