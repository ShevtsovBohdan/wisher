package com.springboot.interfaces;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * UserService is a base Interface class used for creating UserDetails object for login system realisation
 */
public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException;
}
