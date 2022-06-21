package com.example.fastfoodshop.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Sql("/insertOrder.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final OrderRepository orderRepository;

    public OrderRepositoryTest(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    private long idFromTestUser() {
        return jdbcTemplate.queryForObject("SELECT id FROM users WHERE name='test'", Long.class);
    }
    private long idFromTestOrder(){
        return jdbcTemplate.queryForObject("SELECT id FROM orders WHERE user_id="+idFromTestUser(), Long.class);
    }

    @Test
    void findById(){
        assertThat(orderRepository.findById(idFromTestOrder()))
                .hasValueSatisfying(order -> assertThat(order.getUser_id()).isPositive());
    }
}
