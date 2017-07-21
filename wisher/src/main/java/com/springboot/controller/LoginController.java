package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

/**
 * LoginController is the handler class that takes in requests for login users.
 */
@Controller
public class LoginController {

    /**
     * Returns the page where users logging occurs.
     *
     * @param error parameter that informs about entering incorrect data.
     * @param logout parameter that informs about user logout.
     * @param model transfers parameters to the page that would be shown.
     * @return name of the page that would be shown.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout,
                               Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);

        return "login";
    }
}
