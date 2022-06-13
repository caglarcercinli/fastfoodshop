package com.example.fastfoodshop.repositories;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Sql("/insertUser.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private UserRepository repository;

    private long idFromTestUser() {
        return jdbcTemplate.queryForObject("SELECT id FROM users WHERE name='test'", Long.class);
    }

    @Test
    public void findById() {
        assertThat(repository.findById(idFromTestUser()))
                .hasValueSatisfying(user -> assertThat(user.getName()).isEqualTo("test"));
    }
}