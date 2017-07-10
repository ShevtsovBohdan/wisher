package com.springboot.controller;

import com.springboot.dao.ManageUser;
import com.springboot.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

    @RequestMapping(value = "/")
    public String getMainPage(){
        return "hello";
    }

    @RequestMapping("/login")
    public String getLoginPage(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout,
                               Model model){
        model.addAttribute("error",error != null );
        model.addAttribute("logout", logout != null);
        return "login";
    }
}
