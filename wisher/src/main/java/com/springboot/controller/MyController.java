package com.springboot.controller;

import com.springboot.dao.ManageUser;
import com.springboot.dao.ManageWish;
import com.springboot.domain.User;
import com.springboot.domain.Wishes;
import com.springboot.validate.TakeAWish;
import org.hibernate.exception.ConstraintViolationException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class MyController {

    private static ManageUser manageUser = new ManageUser();
    private static ManageWish manageWish = new ManageWish();

    public static User getActiveUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();

        User activeUser = manageUser.findbyUsername(user.getUsername());
        return activeUser;
    }

//    @RequestMapping(value = "/")
//    public ResponseEntity<Wishes> getMainPage() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
//
//
//        Wishes wish = manageWish.findbyWishername("nnn");
////        User user1 = manageUser.findbyUsername(user.getUsername());
//
//        return new ResponseEntity<Wishes>(wish, HttpStatus.OK);
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(ModelMap model) {


        List<Wishes> list = getActiveUser().getWishes();
        model.addAttribute("list", list);

        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "action=addWish")
    public String takeAWish(@ModelAttribute("takeAWish") TakeAWish takeAWish,
                            ModelMap model) {
        Wishes wish = new Wishes();
        wish.setWishName(takeAWish.getName());
        wish.setLink(takeAWish.getLink());

        wish.setUser(getActiveUser());

        Integer ii = manageWish.addWish(wish);

        List<Wishes> list = getActiveUser().getWishes();
        model.addAttribute("list", list);

        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "action=deleteWish")
    public String deleteAWish(@ModelAttribute("takeAWish") TakeAWish takeAWish,
                            ModelMap model) {

        manageWish.deleteWish(takeAWish.getName());

        List<Wishes> list = getActiveUser().getWishes();
        model.addAttribute("list", list);

        return "home";
    }


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
                             @ModelAttribute("userValid") com.springboot.validate.Validator validForm,
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
            return "home";
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
}
