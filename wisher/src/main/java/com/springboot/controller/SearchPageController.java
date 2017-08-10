package com.springboot.controller;

import com.springboot.domain.User;
import com.springboot.domain.Wish;
import com.springboot.userdetails.interfaces.GetUser;
import com.springboot.components.interfaces.ListOrganizer;
import com.springboot.persistence.interfaces.ManageUser;
import com.springboot.persistence.interfaces.ManageWish;
import com.springboot.models.WishValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * SearchPageController is the handler class that takes in requests for searching wishes.
 */
@Controller
public class SearchPageController {
    @Autowired
    private ManageUser manageUser;
    @Autowired
    private ManageWish manageWish;
    @Autowired
    private ListOrganizer listOrganizer;
    @Autowired
    private GetUser getUser;

    /**
     * Handle requests about searching wishes of concrete user.
     *
     * @param searchData sets part of a wish name on which the search will be carried out.
     * @param model transfers parameters to the page that would be shown.
     * @return name of the page that would be shown.
     */
    @RequestMapping(value = "/view/search", method = RequestMethod.GET)
    public String getSearchPage(@ModelAttribute com.springboot.models.SearchDTO searchData,
                                ModelMap model) {
        String searchRequest = searchData.getSearchRequest();

        if (searchData.isSearchRequestEmpty()){
            return "redirect:/view?page=1";
        }
        List<Wish> searchResultList = manageWish.search(getUser.getActiveUser().userID, searchRequest);
        model.addAttribute("list", searchResultList);
        model.addAttribute(new WishValidation());

        return "searchResult";
    }

    /**
     * Handle requests about searching wishes of all user.
     *
     * @param searchData sets part of a wish name on which the search will be carried out.
     * @param model transfers parameters to the page that would be shown.
     * @return name of the page that would be shown.
     */
    @RequestMapping(value = "/view/searchall", method = RequestMethod.GET)
    public String getSearchAllUsersPage(@ModelAttribute com.springboot.models.SearchDTO searchData,
                                ModelMap model) {

        String searchRequest = searchData.getSearchRequest();

        if (searchData.isSearchRequestEmpty()){
            return "redirect:/checkallusers/1";
        }
        List<Wish> searchResultList = manageWish.searchAllWishes(searchRequest);
        List<User> userList;
        userList = listOrganizer.shape(searchResultList);

        model.addAttribute("listWishes", searchResultList);
        model.addAttribute("listUsers", userList);

        return "searchAllUsersResult";
    }

}
