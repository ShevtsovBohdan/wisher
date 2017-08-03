package com.springboot.controller;

import com.springboot.interfaces.FileConverter;
import com.springboot.interfaces.FileTypeVerification;
import com.springboot.interfaces.SavingImageHandler;
import com.springboot.models.CommandObjectForWishID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
public class ImageSaverController {

    @Autowired
    private SavingImageHandler savingImageHandler;

    @Autowired
    private FileTypeVerification fileTypeVerification;

    @Autowired
    private FileConverter fileConverter;


    @RequestMapping(value = "/addLocalLink", method = RequestMethod.POST)
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<String> addWishLink(@RequestParam(value = "file", required = false) MultipartFile file,
                                              @Valid CommandObjectForWishID commandObjectForWishID, BindingResult bindingResult) throws Exception{

        if(bindingResult.hasErrors()){
            String ss = bindingResult.getFieldError().getField();
            return new ResponseEntity<>(bindingResult.getFieldError().getField(), HttpStatus.EXPECTATION_FAILED);
        }

        if(file == null){
            return new ResponseEntity<>("File was not found", HttpStatus.NOT_FOUND);
        }else {
            if (fileTypeVerification.checkFileExtension(file) == false){
                return new ResponseEntity<>("Incorrect type of file", HttpStatus.EXPECTATION_FAILED);
            }
        }


        savingImageHandler.saveImageToTheDirectoryAndToTheDataBase(file, Integer.parseInt(commandObjectForWishID.getWishID()));

        return new ResponseEntity<>(fileConverter.toBase64(file), HttpStatus.OK) ;
    }

}
