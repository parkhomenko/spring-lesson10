package com.hillel.springboot.school.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/students",
                        "/h2database"
                ).permitAll()
                .antMatchers("/student/**").hasAuthority("CUSTOMER")
                .antMatchers("/teachers/**").hasAuthority("ADMIN")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity
                .ignoring()
                .antMatchers("/static/**")
                .antMatchers("/h2database/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select u.login as username, u.password, u.enabled from users u " +
                        "where u.login = ?")
                .authoritiesByUsernameQuery("select u.login as username, r.name as role " +
                        "from user_roles ur join users u on ur.user_id = u.id " +
                        "join roles r on ur.role_id = r.id where u.login=?");
        /*authBuilder.inMemoryAuthentication()
                .withUser("test")
                .password("test")
                .roles("CUSTOMER");*/
    }
}
