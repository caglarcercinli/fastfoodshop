package com.example.fastfoodshop.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String MANAGER = "manager";
    private final DataSource dataSource;

    SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select customers.name as username, customers.password as password, enabled as enabled" +
                                " from customers where name = ?")
                .authoritiesByUsernameQuery("select customers.name as username, rolls.name as authorities"+
                        " from customers inner join clientsrolls"+
                        " on customers.id = clientsrolls.customer_id"+
                        " inner join rolls"+
                        " on rolls.id = clientsrolls.roll_id"+
                        " where customers.name = ?"
                );
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests(requests -> requests
                .mvcMatchers("/customers")
                .hasAuthority(MANAGER)
        );
    }
}
