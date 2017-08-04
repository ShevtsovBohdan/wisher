package com.springboot.controller;

import com.springboot.interfaces.ManageWish;
import com.springboot.models.RequestParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = {"/deletewish"}, method = RequestMethod.POST)
    @ResponseBody
    public String deleteWish(RequestParameters requestParameters) {
        manageWish.deleteWish(requestParameters.getDeleteWishId());
        return "Okey";
    }


}
