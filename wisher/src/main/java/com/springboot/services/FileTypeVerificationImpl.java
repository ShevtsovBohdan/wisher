package com.springboot.services;


import com.springboot.interfaces.FileTypeVerification;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
public class FileTypeVerificationImpl implements FileTypeVerification{
    @Override
    public boolean checkFileExtension(MultipartFile file){
        String filename = file.getContentType();
        String[] imageChecking = filename.split("/");
        if (!imageChecking[0].equals("image")){
            return false;
        }else return true;
    }
}
