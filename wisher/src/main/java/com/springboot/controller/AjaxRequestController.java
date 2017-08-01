package com.springboot.controller;

import com.springboot.interfaces.ImageToBytes;
import com.springboot.models.FormData;
import javafx.scene.shape.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class AjaxRequestController {

    @Autowired
    private ImageToBytes imageToBytes;


    @RequestMapping(value = "/addLocalLink", method = RequestMethod.POST)
    @ResponseBody
    public String addWishLink(@RequestParam("file") MultipartFile file, FormData formData) throws IOException{

        byte[] bytes = file.getBytes();

        String localPath = imageToBytes.reformat(bytes, 50);

        return localPath;
    }
}
