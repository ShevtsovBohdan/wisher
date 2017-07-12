package com.springboot.controller;

import com.springboot.dao.ManageUser;
import com.springboot.domain.User;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

    private ManageUser manageUser = new ManageUser();


    @RequestMapping(value = "/")
    public ResponseEntity<User> getMainPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();

        User user1 = manageUser.findbyUsername(user.getUsername());

        return new ResponseEntity<User>(user1, HttpStatus.OK);
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
