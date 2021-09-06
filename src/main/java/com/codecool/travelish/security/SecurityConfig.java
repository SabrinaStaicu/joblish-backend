package com.codecool.travelish.security;

import com.codecool.travelish.security.fillter.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/login/**").permitAll();
        http.authorizeRequests().antMatchers("/register").permitAll();
        http.authorizeRequests().antMatchers("/jobs/filter-by-company/**").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers("/jobs/add-favorites/**").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers("/jobs/remove-favorites/**").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers("/jobs/remove-favorites/**").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers("/jobs/favorites-contain-job/**").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers("/jobs/get-favorite-jobs/**").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers("/applications/**").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers("/companies/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/users/**").hasAnyAuthority("ADMIN");
        http.formLogin();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
