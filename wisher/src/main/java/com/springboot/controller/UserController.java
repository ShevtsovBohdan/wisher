package com.springboot.controller;

import com.springboot.dao.ManageWishImpl;
import com.springboot.domain.User;
import com.springboot.domain.Wish;
import com.springboot.interfaces.ManageUser;
import com.springboot.interfaces.ManageWish;
import com.springboot.models.RegistrationValidation;
import com.springboot.models.TakeAWish;
import com.springboot.models.WishValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private ManageUser manageUser;
    @Autowired
    private ManageWish manageWish;

    public User getActiveUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        User activeUser = manageUser.findbyUserName(user.getUsername());
        return activeUser;
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String getMainPage(ModelMap model) {
//
//
//        List<Wish> list = getActiveUser().getWishes();
//        model.addAttribute("list", list);
//
//        model.addAttribute("manageWish", new ManageWishImpl());
//
//        return "home";
//    }

    @RequestMapping(value = {"/{wishID}", "/"}, method = RequestMethod.GET)
    public String getMainPage(@PathVariable(value = "wishID", required = false) Integer wishID,
                              ModelMap model) {
        model.addAttribute(new WishValidation());
        if (wishID != null) {
            manageWish.deleteWish(wishID);
            return "redirect:/";
        } else {
            List<Wish> list = getActiveUser().getWishes();
            model.addAttribute("list", list);

            model.addAttribute("manageWish", new ManageWishImpl());
            return "home";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String takeAWish(@ModelAttribute("takeAWish") TakeAWish takeAWish,
                            @Valid WishValidation wishValidation, BindingResult bindingResult,
                            ModelMap model) {
        if(bindingResult.hasErrors()){
            return "home";
        }

        manageWish.addWish(takeAWish.getName(), takeAWish.getLink(), getActiveUser());

        List<Wish> list = getActiveUser().getWishes();
        model.addAttribute("list", list);

        return "home";
    }

    @RequestMapping(value = "/checkallusers")
    public String getAllUsers(Model model) {

        List<Wish> listWishes = manageWish.listWishes();
        model.addAttribute("listWishes", listWishes);


        List<User> listUsers = manageUser.listUsers();
        model.addAttribute("listUsers", listUsers);


        return "allusers";
    }
}
