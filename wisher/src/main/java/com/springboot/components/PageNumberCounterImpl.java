package com.springboot.components;

import com.springboot.dao.ManageWishImpl;
import com.springboot.domain.User;
import com.springboot.interfaces.ManageUser;
import com.springboot.interfaces.ManageWish;
import com.springboot.interfaces.PageNumberCounter;
import com.springboot.userdetails.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Class is used for counting number of pages(for pagination) according to number of wishes in the database
 */
@Component
public class PageNumberCounterImpl implements PageNumberCounter{
    public static final int MAX_ELEMENTS_ON_THE_ALLUSERS_PAGE = 15;

    @Autowired
    ManageWish manageWish;
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
        UserHelper user = new UserHelper(authentication.getPrincipal()) ;
        User activeUser = manageUser.findbyUserName(user.getUsername());
        return activeUser;
    }

    /**
     * Return pagination for page where all users wishes displayed
     *
     * @return pagination for page where all users wishes displayed
     */
    @Override
    public long countForAllUsersPage(){
        long numberOfPages;
        if (manageWish.numberOfRows() < MAX_ELEMENTS_ON_THE_ALLUSERS_PAGE){
            numberOfPages = 1;
        }else{
            numberOfPages = (manageWish.numberOfRows() + MAX_ELEMENTS_ON_THE_ALLUSERS_PAGE - 1) / MAX_ELEMENTS_ON_THE_ALLUSERS_PAGE;
        }

        //Test for correct Pages number
        if (numberOfPages == 0){
            numberOfPages = 1;
        }
        return numberOfPages;
    }

    /**
     * Return pagination for page where all users wishes displayed
     *
     * @return pagination for page where all users wishes displayed
     */
    @Override
    public long countForMainPage(){
        long numberOfPages;
        Long numberOfRows = manageWish.numberOfRows(getActiveUser().userID);

        if (numberOfRows < ManageWishImpl.MAX_ELEMENT_ON_THE_PAGE){
            numberOfPages = 1;
        }else{
            numberOfPages = (numberOfRows + ManageWishImpl.MAX_ELEMENT_ON_THE_PAGE - 1) / ManageWishImpl.MAX_ELEMENT_ON_THE_PAGE;
        }
        //Test for correct Pages number
        if (numberOfPages == 0){
            numberOfPages = 1;
        }
        return numberOfPages;
    }


}
