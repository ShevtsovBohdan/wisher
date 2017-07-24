package com.springboot.controller;

import com.springboot.components.ListOrganizer;
import com.springboot.components.PageNumberCounter;
import com.springboot.domain.Wish;
import com.springboot.interfaces.ManageWish;
import com.springboot.models.RequestParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;


/**
 * AllUsersPageController is the handler class that takes in requests for overviewing all user's wishes.
 */
@Controller
public class AllUsersPageController {
    @Autowired
    private ManageWish manageWish;

    @Autowired
    private PageNumberCounter pageNumberCounter;

    @Autowired
    private ListOrganizer listOrganizer;

    /**
     * Handle requests for overviewing all user's wishes.
     *
     * @param model transfers parameters to the page that would be shown.
     * @return name of the page that would be shown.
     */
    @RequestMapping(value = "/checkallusers/{startValue}", method = RequestMethod.GET)
    public String getAllUsers(RequestParameters requestParameters, Model model) {
        List<Wish> wishList;
        wishList = manageWish.listAllUsersWishes(requestParameters.getStartValue(), PageNumberCounter.MAX_ELEMENTS_ON_THE_ALLUSERS_PAGE);

        model.addAttribute("rowsNumber", pageNumberCounter.countForAllUsersPage());

        model.addAttribute("listWishes", wishList);
        model.addAttribute("listUsers", listOrganizer.shape(wishList));

        return "allusers";
    }
}
