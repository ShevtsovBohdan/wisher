package com.springboot.userdetails.implementations;

import com.springboot.domain.User;
import com.springboot.persistence.interfaces.ManageUser;
import com.springboot.userdetails.UserHelper;
import com.springboot.userdetails.interfaces.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetUserImpl implements GetUser {

    @Autowired
    ManageUser manageUser;

    /**
     * Returns data about user that was logged in.
     *
     * @return data about user that was logged in.
     */
    @Override
    public User getActiveUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserHelper user = new UserHelper(authentication.getPrincipal());
        User activeUser = manageUser.findbyUserName(user.getUsername());
        return activeUser;

    }
}
