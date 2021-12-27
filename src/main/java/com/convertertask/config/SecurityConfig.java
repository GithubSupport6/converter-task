package com.convertertask.config;

import com.convertertask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        //TODO: Плохое решение, но без него на POST 403 ошибка. Нужно поправить.
        security.csrf().disable();
        security.authorizeRequests()
                .antMatchers("/login").not().fullyAuthenticated()
                .antMatchers("/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
                .and()
                .logout().permitAll().logoutSuccessUrl("/login");
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService);
    }
}
