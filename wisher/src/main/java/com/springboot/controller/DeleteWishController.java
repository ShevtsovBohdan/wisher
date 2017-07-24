package com.springboot.controller;

import com.springboot.interfaces.ManageWish;
import com.springboot.models.RequestParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * DeleteUserController is the handler class that takes in requests for deleting wishes.
 */
@Controller
public class DeleteWishController {

    @Autowired
    private ManageWish manageWish;

    /**
     * Handles requests for deleting wishes by wishID.
     *
     * @return the URL for the page where the wishes can be overviewing.
     */
    //Use command
    @RequestMapping(value = {"/deletewish/{wishID}"}, method = RequestMethod.GET)
    public String deleteWish(RequestParameters requestParameters) {

        manageWish.deleteWish(requestParameters.getWishID());
        return "redirect:/view?page="+ requestParameters.getPage();
    }
}
