package com.springboot.controller;

import com.springboot.interfaces.ManageWish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
     * @param wishID Id of the wish that would be delete.
     * @param viewPageNumb Number of the page where the wish was deleted.
     * @return the URL for the page where the wishes can be overviewing.
     */
    @RequestMapping(value = {"/deletewish/{wishID}"}, method = RequestMethod.GET)
    public String deleteWish(@PathVariable(value = "wishID") Integer wishID,
                             @RequestParam(value = "page") Integer viewPageNumb) {

        manageWish.deleteWish(wishID);
        return "redirect:/view?page="+viewPageNumb;
    }
}
