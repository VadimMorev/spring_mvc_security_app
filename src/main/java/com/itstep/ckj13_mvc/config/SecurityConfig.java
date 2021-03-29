package com.itstep.ckj13_mvc.config;

import com.itstep.ckj13_mvc.service.UserSecurityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserSecurityDetailsService detailsService;

    @Autowired
    public SecurityConfig(UserSecurityDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/notes/**").authenticated()
                .antMatchers("/admin-page/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/manager-page/**").hasAuthority("ROLE_MANAGER")
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(detailsService);
        provider.setPasswordEncoder(encoder());
        auth.authenticationProvider(provider);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
