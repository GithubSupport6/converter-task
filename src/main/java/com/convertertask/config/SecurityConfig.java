package com.convertertask.config;

import com.convertertask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        //TODO: Плохое решение, но без него на POST возвращается 403 результат. Нужно поправить, но пока не знаю как.
        security.csrf().disable();
        security.authorizeRequests().anyRequest().permitAll();
        //security.authorizeRequests()
        //        .antMatchers("/signup","/js/signup.js").not().fullyAuthenticated()
        //        .antMatchers("/login").permitAll()
        //        .anyRequest().authenticated()
        //        .and()
        //        .formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
        //        .and()
        //        .logout().permitAll().logoutSuccessUrl("/login");
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService);
    }
}
