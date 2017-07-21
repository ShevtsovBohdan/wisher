package com.springboot.controller;

import com.springboot.domain.User;
import com.springboot.domain.Wish;
import com.springboot.interfaces.ManageUser;
import com.springboot.interfaces.ManageWish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * AllUsersPageController is the handler class that takes in requests for overviewing all user's wishes.
 */
@Controller
public class AllUsersPageController {
    @Autowired
    private ManageWish manageWish;

    private static final int MAX_ELEMENTS_ON_THE_PAGE = 15;

    /**
     * Handle requests for overviewing all user's wishes.
     *
     * @param startValue sets start point for the table where returning objects start takes.
     * @param model transfers parameters to the page that would be shown.
     * @return name of the page that would be shown.
     */
    @RequestMapping(value = "/checkallusers/{startValue}", method = RequestMethod.GET)
    public String getAllUsers(@PathVariable(value = "startValue") Integer startValue,
                              Model model) {
        long attributeRowsNumberValue = 0;

        List<Wish> wishList = new LinkedList<>();
        List<User> userList = new LinkedList<>();

        wishList = manageWish.listAllUsersWishes(startValue, MAX_ELEMENTS_ON_THE_PAGE);
        Iterator<Wish> iterator = wishList.iterator();

        while(iterator.hasNext()){
            Wish wish = iterator.next();
            if(userList.contains(wish.getUser())){

            }else{
                userList.add(wish.getUser());
            }
        }
        long numberOfRows = manageWish.numberOfRows();

        if (numberOfRows < MAX_ELEMENTS_ON_THE_PAGE){
            attributeRowsNumberValue = 1;
        }else{
            attributeRowsNumberValue = (numberOfRows + MAX_ELEMENTS_ON_THE_PAGE - 1) / MAX_ELEMENTS_ON_THE_PAGE;
        }
        model.addAttribute("rowsNumber", attributeRowsNumberValue);

        model.addAttribute("listWishes", wishList);
        model.addAttribute("listUsers", userList);


        return "allusers";
    }
}
