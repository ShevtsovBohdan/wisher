package com.springboot.components;

import com.springboot.persistence.ManageWishImpl;
import com.springboot.userdetails.interfaces.GetUser;
import com.springboot.persistence.interfaces.ManageWish;
import com.springboot.components.interfaces.PageNumberCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class is used for counting number of pages(for pagination) according to number of wishes in the database
 */
@Component
public class PageNumberCounterImpl implements PageNumberCounter{
    public static final int MAX_ELEMENTS_ON_THE_ALLUSERS_PAGE = 15;

    @Autowired
    public ManageWish manageWish;

    @Autowired
    public GetUser getUser;
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
        Long numberOfRows = manageWish.numberOfRows(getUser.getActiveUser().userID);

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
