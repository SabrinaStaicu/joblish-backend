package com.codecool.travelish.security;

import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.repository.AppUserRepository;
import com.codecool.travelish.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/*
 There must be a class that implements UserDetailsService otherwise Spring will create a default
 UserDetailsService (an instance of InMemoryUserDetailsManager, see UserDetailsServiceAutoConfiguration) that does not
 know where to find users for authentication.
*/
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserService appUserService;

    @Autowired
    public CustomUserDetailsService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    /**
     * Loads the user from the DB and converts it to Spring Security's internal User oobject
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserService.findByEmail(username);

        return new User(user.getEmail(), user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList()));
    }
}
