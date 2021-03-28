package com.itstep.ckj13_mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/notes/**").authenticated()
                .antMatchers("/admin-page/**").hasRole("ADMIN")
                .antMatchers("/admin-page/**").hasRole("MANAGER")
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetails user1 = User.builder()
                .username("user")
                .password(encoder().encode("1234"))
                .roles("USER")
                .build();
        UserDetails user2 = User.builder()
                .username("admin")
                .password(encoder().encode("5678"))
                .roles("ADMIN")
                .build();
        UserDetails user3 = User.builder()
                .username("manager")
                .password(encoder().encode("1357"))
                .roles("MANAGER")
                .build();
        auth.inMemoryAuthentication().withUser(user1).withUser(user2).withUser(user3);
    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
