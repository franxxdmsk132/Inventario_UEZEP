package com.eabmodel.inventario_uezep.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("mamor").password("{noop}pollo").roles("manager")
                .and()
                .withUser("xd").password("{noop}xdnt").roles("manager");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .cors().and()
        .csrf().disable().authorizeRequests()
        .antMatchers("/users").hasRole("manager")
        .anyRequest().authenticated()
        .and()
        .formLogin();
    }

}

