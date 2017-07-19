package com.springboot.controller;

import com.springboot.interfaces.ManageUser;
import com.springboot.models.RegistrationValidation;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private ManageUser manageUser;


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String getRegPage(@ModelAttribute com.springboot.domain.User userForm,
                             @ModelAttribute com.springboot.models.RegistrValidator validForm,
                             @Valid RegistrationValidation registrationValidation, BindingResult bindingResult,
                             Model model) {
        if (validForm.getPasswordconf().equals(userForm.getPassword()) != true) {
            return "redirect:/registration?checkforpass";
        } else {
            try {
                manageUser.addUser(userForm.getUsername(), userForm.getPassword(), "USER");
            } catch (ConstraintViolationException e) {
                return "redirect:/registration?userfound";
            }
        }
        if (bindingResult.hasErrors()) {
                return "registration";
            }
        return "redirect:/view/1";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegPage(@RequestParam(value = "checkforpass", required = false) String checkforpass,
                             @RequestParam(value = "userfound", required = false) String userfound,
                             Model model) {
        model.addAttribute("checkforpass", checkforpass != null);
        model.addAttribute("userfound", userfound != null);
        model.addAttribute(new RegistrationValidation());
        return "registration";
    }


}
