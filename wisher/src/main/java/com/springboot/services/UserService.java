package com.springboot.services;


import com.springboot.dao.ManageUserImpl;
import com.springboot.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService{


    private User user = new User();

    private ManageUserImpl manageUserImpl = new ManageUserImpl();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user = manageUserImpl.findbyUsername(username);
        if(user != null) {

            Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getAuthorities()));

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else throw new UsernameNotFoundException("Username not found");

    }
}
