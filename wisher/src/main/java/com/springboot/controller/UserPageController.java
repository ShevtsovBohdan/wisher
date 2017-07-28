package com.springboot.controller;

import com.springboot.components.PageNumberCounterImpl;
import com.springboot.domain.User;
import com.springboot.domain.Wish;
import com.springboot.interfaces.GetUser;
import com.springboot.interfaces.ManageUser;
import com.springboot.interfaces.ManageWish;
import com.springboot.interfaces.PageNumberCounter;
import com.springboot.models.RequestParameters;
import com.springboot.models.WishValidation;
import com.springboot.userdetails.UserHelper;
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
//rename
@Controller
public class UserPageController {

    @Autowired
    private ManageUser manageUser;
    @Autowired
    private ManageWish manageWish;
    @Autowired
    private PageNumberCounter pageNumberCounter;
    @Autowired
    private GetUser getUser;

    //Use proper style
    private final static int DEFAULT_PAGE_NUMBER = 1;

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
     * @param model transfers parameters to the page that would be shown.
     * @return name of the page that would be shown.
     */
    //Use command for params
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String getMainPage(@Valid RequestParameters requestParameters, BindingResult bindingResult,
                              ModelMap model) {

        if(bindingResult.hasErrors()){
            return "redirect:/view?page=1";
        }

        List<Wish> activeWishesList = manageWish.listWishes(requestParameters.getPage(), getUser.getActiveUser().userID);

        model.addAttribute("rowsNumber", pageNumberCounter.countForMainPage());
        model.addAttribute(new WishValidation());

        model.addAttribute("list", activeWishesList);
        model.addAttribute("viewPageNumb", requestParameters.getPage());

        return "home";

    }

    /**
     * Handle requests for adding wish to the list.
     *
     * @param wishValidation verifies the correctness of the entered data.
     * @param bindingResult contains validation results.
     * @param model transfers parameters to the page that would be shown.
     * @return name of the page that would be shown.
     */
    //Use command for params
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public String putWish(RequestParameters requestParameters,
                          @Valid WishValidation wishValidation, BindingResult bindingResult,
                          ModelMap model) {
        if (!bindingResult.hasErrors()) {
            manageWish.addWish(wishValidation.getWishName(), wishValidation.getLink(), getUser.getActiveUser());
        }
        List<Wish> activeWishesList = manageWish.listWishes(DEFAULT_PAGE_NUMBER, getUser.getActiveUser().userID);
        model.addAttribute("list", activeWishesList);

        model.addAttribute("rowsNumber", pageNumberCounter.countForMainPage());
        model.addAttribute("viewPageNumb", requestParameters.getPage());
        return "home";

    }
}
