package com.springboot.controller;

import com.springboot.dao.ManageWishImpl;
import com.springboot.domain.User;
import com.springboot.domain.Wish;
import com.springboot.interfaces.ManageUser;
import com.springboot.interfaces.ManageWish;
import com.springboot.models.TakeAWish;
import com.springboot.models.WishValidation;
import com.springboot.services.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * MainPageController is the handler class that takes in requests for overviewing main page.
 */
@Controller
public class MainPageController {

    @Autowired
    private ManageUser manageUser;
    @Autowired
    private ManageWish manageWish;

    private final static int defaultPageNumber = 1;

    /**
     * Returns data about user the was logged in.
     *
     * @return data about user the was logged in.
     */
    public User getActiveUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserHelper user = new UserHelper(authentication.getPrincipal()) ;
        User activeUser = manageUser.findbyUserName(user.getUsername());
        return activeUser;
    }

    /**
     * Returns URL of the main page.
     *
     * @param model transfers parameters to the page that would be shown.
     * @return URL of the main page.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(ModelMap model) {
        return "redirect:/view?page=1";
    }

    /**
     * Handle requests for overviewing main page.
     *
     * @param viewPageNumb sets the page number where wishes are overviewing.
     * @param searchData sets part of the wish name on which the search will be carried out.
     * @param model transfers parameters to the page that would be shown.
     * @return name of the page that would be shown.
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String getMainPage(@RequestParam(value = "page") Integer viewPageNumb,
                              @ModelAttribute() com.springboot.models.TakeDataFromTheInputHelper searchData,
                              ModelMap model) {
        long attributeRowsNumberValue = 0;
        Long numberOfRows = manageWish.numberOfRows(getActiveUser().userID);
        attributeRowsNumberValue = (numberOfRows + ManageWishImpl.MAX_ELEMENT_ON_THE_PAGE - 1) / ManageWishImpl.MAX_ELEMENT_ON_THE_PAGE;

        List<Wish> activeWishesList = manageWish.listWishes(viewPageNumb, getActiveUser().userID);

        if (attributeRowsNumberValue == 0) {
           attributeRowsNumberValue = 1;
        }
        model.addAttribute("rowsNumber", attributeRowsNumberValue);
        model.addAttribute(new WishValidation());

        model.addAttribute("list", activeWishesList);
        model.addAttribute("viewPageNumb", viewPageNumb);

        return "home";

    }

    /**
     * Handle requests for adding wish to the list.
     *
     * @param viewPageNumb sets the page number where wishes are overviewing.
     * @param takeAWish takes the wish properties from the form.
     * @param wishValidation verifies the correctness of the entered data.
     * @param bindingResult contains validation results.
     * @param model transfers parameters to the page that would be shown.
     * @return name of the page that would be shown.
     */
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public String putWish(@RequestParam(value = "page") Integer viewPageNumb,
                          @ModelAttribute("takeAWish") TakeAWish takeAWish,
                          @Valid WishValidation wishValidation, BindingResult bindingResult,
                          ModelMap model) {
        if (!bindingResult.hasErrors()) {
            manageWish.addWish(takeAWish.getName(), takeAWish.getLink(), getActiveUser());
        }
        List<Wish> activeWishesList = manageWish.listWishes(defaultPageNumber, getActiveUser().userID);
        model.addAttribute("list", activeWishesList);

        Long numberOfRows = manageWish.numberOfRows(getActiveUser().userID);
        long attributeRowsNumberValue = (numberOfRows + ManageWishImpl.MAX_ELEMENT_ON_THE_PAGE - 1) / ManageWishImpl.MAX_ELEMENT_ON_THE_PAGE;
        if (attributeRowsNumberValue == 0) {
            attributeRowsNumberValue = 1;
        }
        model.addAttribute("rowsNumber", attributeRowsNumberValue);
        model.addAttribute("viewPageNumb", viewPageNumb);
        return "home";

    }
}
