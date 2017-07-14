package com.springboot.controller;

import com.springboot.dao.ManageUser;
import com.springboot.dao.ManageWish;
import com.springboot.domain.User;
import com.springboot.domain.Wishes;
import com.springboot.models.TakeAWish;
import org.hibernate.exception.ConstraintViolationException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyController {

    private ManageUser manageUser = new ManageUser();
    private ManageWish manageWish = new ManageWish();

    public User getActiveUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();

        User activeUser = manageUser.findbyUsername(user.getUsername());
        return activeUser;
    }



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(ModelMap model) {


        List<Wishes> list = getActiveUser().getWishes();
        model.addAttribute("list", list);

        model.addAttribute("manageWish", new ManageWish());

        return "home";
    }

    @RequestMapping(value = "/{wishID}", method = RequestMethod.GET)
    public String getMainPage(@PathVariable(value = "wishID") int wishID,
                              ModelMap model) {

        manageWish.deleteWish(wishID);

        List<Wishes> list = getActiveUser().getWishes();
        model.addAttribute("list", list);

        model.addAttribute("manageWish", new ManageWish());

        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String takeAWish(@ModelAttribute("takeAWish") TakeAWish takeAWish,
                            ModelMap model) {
        Wishes wish = new Wishes();
        wish.setWishName(takeAWish.getName());
        wish.setLink(takeAWish.getLink());

        wish.setUser(getActiveUser());

        manageWish.addWish(wish);

        List<Wishes> list = getActiveUser().getWishes();
        model.addAttribute("list", list);

        return "home";
    }

//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public String deleteAWish(@ModelAttribute("takeAWish") TakeAWish takeAWish,
//                            ModelMap model) {
//
//        manageWish.deleteWish(takeAWish.getName());
//
//        List<Wishes> list = getActiveUser().getWishes();
//        model.addAttribute("list", list);
//
//        return "home";
//    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout,
                               Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);

        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String getRegPage(@ModelAttribute("userForm") com.springboot.domain.User userForm,
                             @ModelAttribute("userValid") com.springboot.models.Validator validForm,
                             @RequestParam(value = "error", required = false) String checkforpass,
                             Model model) {
        model.addAttribute("checkforpass", checkforpass != null);

        if (validForm.getPasswordconf().equals(userForm.getPassword()) != true) {
            return "redirect:/registration?checkforpass";
        } else {
            try {
                manageUser.addUser(userForm.getUsername(), userForm.getPassword(), "USER");
            } catch (ConstraintViolationException e) {
                return "redirect:/registration?userfound";
            }
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegPage(@RequestParam(value = "checkforpass", required = false) String checkforpass,
                             @RequestParam(value = "userfound", required = false) String userfound,
                             Model model) {
        model.addAttribute("checkforpass", checkforpass != null);
        model.addAttribute("userfound", userfound != null);
        return "registration";
    }

    @RequestMapping(value = "/checkallusers")
    public String getAllUsers(Model model) {

        List<Wishes> listWishes = manageWish.listWishes();
        model.addAttribute("listWishes", listWishes);


        List<User> listUsers = manageUser.listUsers();
        model.addAttribute("listUsers", listUsers);


        return "allusers";
    }
}
