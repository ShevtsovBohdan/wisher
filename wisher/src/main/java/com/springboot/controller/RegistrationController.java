package com.springboot.controller;

import com.springboot.persistence.interfaces.ManageUser;
import com.springboot.models.UserDTO;
import com.springboot.models.SearchDTO;
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
     * @param validForm gets the data from the field named "Confirm Password"
     * @param userDTO verifies the correctness of the entered data.
     * @param bindingResult contains validation results.
     * @param model transfers parameters to the page that would be shown.
     * @return name of the page that would be shown.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String getRegPage(@ModelAttribute SearchDTO validForm,
                             @Valid UserDTO userDTO, BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }else {
            try {
                manageUser.addUser(userDTO.getUsername(), userDTO.getPassword(), "USER");
            } catch (ConstraintViolationException e) {
                System.out.println("Exception(use lo4j)" + e.getMessage());
                return "redirect:/registration?userFound";
            }
        }
        return "redirect:/view/1";
    }

    /**
     * Returns the page where users registration occurs.
     *
     * @param checkPassword parameter that informs about failing of confirmation entered password.
     * @param userFound parameter that informs about existing entered username.
     * @param model transfers parameters to the page that would be shown.
     * @return name of the page that would be shown.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegPage(@RequestParam(value = "checkPassword", required = false) String checkPassword,
                             @RequestParam(value = "userFound", required = false) String userFound,
                             Model model) {
        model.addAttribute("checkPassword", checkPassword != null);
        model.addAttribute("userFound", userFound != null);
        model.addAttribute(new UserDTO());
        return "registration";
    }


}
