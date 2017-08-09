package com.springboot.services.impl;

import com.springboot.domain.User;
import com.springboot.persistence.interfaces.ManageUser;
import com.springboot.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Class used for creating UserDetails object for login system realisation
 */
@Service
public class UserServiceImpl implements UserService {

    private User user = new User();

    @Autowired
    private ManageUser manageUser;

    /**
     * Returns UserDetails object that contains information about user loaded from the database by username
     *
     * @param username the name of the user that will be returned from the datamase
     * @return UserDetails object that contains detail information about user
     * @throws UsernameNotFoundException if such username wasn't found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user = manageUser.findbyUserName(username);
        if (user != null) {

            Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getAuthorities()));

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else throw new UsernameNotFoundException("Username not found");

    }
}
