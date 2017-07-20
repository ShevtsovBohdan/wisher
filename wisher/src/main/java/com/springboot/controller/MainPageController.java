package com.springboot.controller;

import com.springboot.dao.ManageWishImpl;
import com.springboot.domain.User;
import com.springboot.domain.Wish;
import com.springboot.interfaces.ManageUser;
import com.springboot.interfaces.ManageWish;
import com.springboot.models.TakeAWish;
import com.springboot.models.WishValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainPageController {
    @Autowired
    private ManageUser manageUser;
    @Autowired
    private ManageWish manageWish;

    private final static int defaultPageNumber = 1;

    public User getActiveUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        User activeUser = manageUser.findbyUserName(user.getUsername());
        return activeUser;
    }

    @RequestMapping(value = {"/", "/view"}, method = RequestMethod.GET)
    public String getMainPage(ModelMap model) {
        return "redirect:/view/1";
    }


    /**
     * Old
     */
//    @RequestMapping(value = {"/{wishID}", "/"}, method = RequestMethod.GET)
//    public String getMainPage(@PathVariable(value = "wishID", required = false) Integer wishID,
//                              ModelMap model) {
//
//        if (wishID != null) {
//            manageWish.deleteWish(wishID);
//            return "redirect:/";
//        } else {
//            Number number = (Number) manageWish.numberOfRows();
//            model.addAttribute("rowsNumber", number);
//            model.addAttribute(new WishValidation());
//
//            List<Wish> list = getActiveUser().getWishes();
//            model.addAttribute("list", list);
//
//            model.addAttribute("manageWish", new ManageWishImpl());
//            return "home";
//        }
//    }

    @RequestMapping(value = {"/deletewish/{wishID}/{viewPageNumb}"}, method = RequestMethod.GET)
    public String deleteWish(@PathVariable(value = "wishID") Integer wishID,
                             @PathVariable(value = "viewPageNumb") Integer viewPageNumb,
                              ModelMap model) {

        manageWish.deleteWish(wishID);
        return "redirect:/view/"+viewPageNumb;
    }


    @RequestMapping(value = "/view/{viewPageNumb}", method = RequestMethod.GET)
    public String getMainPage(@PathVariable(value = "viewPageNumb") Integer viewPageNumb,
                              ModelMap model) {
        List<Wish> activeWishesList = manageWish.listWishes(viewPageNumb, getActiveUser().userID);
        Long numberOfRows = manageWish.numberOfRows(getActiveUser().userID);

        long attributeRowsNumberValue = (numberOfRows + ManageWishImpl.MAX_ELEMENT_ON_THE_PAGE - 1) / ManageWishImpl.MAX_ELEMENT_ON_THE_PAGE;
        if (attributeRowsNumberValue == 0) {
           attributeRowsNumberValue = 1;
        }
        model.addAttribute("rowsNumber", attributeRowsNumberValue);
        model.addAttribute(new WishValidation());

        model.addAttribute("list", activeWishesList);

        model.addAttribute(new WishValidation());
        model.addAttribute("viewPageNumb", viewPageNumb);

//            model.addAttribute("manageWish", new ManageWishImpl());
        return "home";

    }


//    @RequestMapping(value = "/wishespage/{startNumb}", method = RequestMethod.GET)
//    public String getSomeWishes(@PathVariable(value = "startNumb") Integer startNumb){
//        List<Wish> list = manageWish.listWishes(startNumb);
//
//
//        return "redirect:/";
//    }

    @RequestMapping(value = "/view/{viewPageNumber}", method = RequestMethod.POST)
    public String putWish(@ModelAttribute("takeAWish") TakeAWish takeAWish,
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
//        model.addAttribute("viewPageNumb", viewPageNumb);
        return "home";

    }
}
