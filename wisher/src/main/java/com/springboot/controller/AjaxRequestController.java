package com.springboot.controller;

import com.springboot.interfaces.ImageToBytes;
import com.springboot.interfaces.ManageWish;
import com.springboot.models.FormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.Convert;
import java.io.IOException;
import java.util.Base64;

@Controller
public class AjaxRequestController {

    @Autowired
    private ImageToBytes imageToBytes;

    @Autowired
    private ManageWish manageWish;


    @RequestMapping(value = "/addLocalLink", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<byte[]> addWishLink(@RequestParam(value = "file") MultipartFile file, FormData formData) throws Exception{

        byte[] bytes = file.getBytes();

        byte[] base64Bytes = Base64.getEncoder().encode(bytes);

        String pathToFile = imageToBytes.reformat(bytes, Integer.parseInt(formData.getWishID()));

        manageWish.saveWishLocalLink(Integer.parseInt(formData.getWishID()), pathToFile);

        return new ResponseEntity<>(base64Bytes, HttpStatus.OK) ;
    }
}
