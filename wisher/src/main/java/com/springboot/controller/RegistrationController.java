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

/**
 * RegistrationController is the handler class that takes in requests for users registration.
 */
@Controller
public class RegistrationController {
    @Autowired
    private ManageUser manageUser;

    /**
     * Handle properties of the user that was entered to the form.
     *
     * @param userForm gets the user name and password from the form
     * @param validForm gets the data from the field named "Confirm Password"
     * @param registrationValidation verifies the correctness of the entered data.
     * @param bindingResult contains validation results.
     * @param model transfers parameters to the page that would be shown.
     * @return name of the page that would be shown.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String getRegPage(@ModelAttribute com.springboot.domain.User userForm,
                             @ModelAttribute com.springboot.models.TakeDataFromTheInputHelper validForm,
                             @Valid RegistrationValidation registrationValidation, BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!validForm.getPasswordconf().equals(userForm.getPassword())) {
            return "redirect:/registration?checkforpass";
        } else {
            try {
                manageUser.addUser(userForm.getUsername(), userForm.getPassword(), "USER");
            } catch (ConstraintViolationException e) {
                return "redirect:/registration?userfound";
            }
        }

        return "redirect:/view/1";
    }

    /**
     * Returns the page where users registration occurs.
     *
     * @param checkPassword parameter that informs about failing of confirmation entered password.
     * @param userfound parameter that informs about existing entered username.
     * @param model transfers parameters to the page that would be shown.
     * @return name of the page that would be shown.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegPage(@RequestParam(value = "checkPassword", required = false) String checkPassword,
                             @RequestParam(value = "userfound", required = false) String userfound,
                             Model model) {
        model.addAttribute("checkPassword", checkPassword != null);
        model.addAttribute("userfound", userfound != null);
        model.addAttribute(new RegistrationValidation());
        return "registration";
    }


}
