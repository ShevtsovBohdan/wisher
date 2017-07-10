package com.springboot.services;


import com.springboot.dao.ManageUser;
import com.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private User user;

    @Autowired
    private ManageUser manageUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user = manageUser.findbyUsername(username);
        if(user != null) {

            Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getAuthorities()));

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else throw new UsernameNotFoundException("Username not found");

    }
}
