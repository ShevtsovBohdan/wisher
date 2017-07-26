package com.springboot.userdetails;

import com.springboot.domain.User;
import com.springboot.interfaces.GetUser;
import com.springboot.interfaces.ManageUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetActiveUser implements GetUser {

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
