package com.codecool.travelish.security;

import com.codecool.travelish.model.company.Company;
import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.repository.AppUserRepository;
import com.codecool.travelish.service.AppUserService;
import com.codecool.travelish.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserService appUserService;
    private final CompanyService companyService;

    @Autowired
    public CustomUserDetailsService(AppUserService appUserService, CompanyService companyService) {
        this.appUserService = appUserService;
        this.companyService = companyService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (appUserService.existsByEmail(username)) {
            AppUser user = appUserService.findByEmail(username);
            return new User(user.getEmail(), user.getPassword(),
                    user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList()));
        }
        Company company = companyService.findByEmail(username);
        return new User(company.getEmail(), company.getPassword(), company.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList()));
    }
}
