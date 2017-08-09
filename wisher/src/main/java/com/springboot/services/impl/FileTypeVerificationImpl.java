package com.springboot.services.impl;


import com.springboot.services.interfaces.FileTypeVerification;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Class that used for verification file that was uploaded by user.
 */
@Component
public class FileTypeVerificationImpl implements FileTypeVerification {
    /**
     * Verifies file if it is image or not.
     *
     * @param file uploaded file by user.
     * @return false if it is not an image and true if it is an image
     */
    @Override
    public boolean checkFileExtension(MultipartFile file){
        String filename = file.getContentType();
        String[] imageChecking = filename.split("/");
        if (!imageChecking[0].equals("image")){
            return false;
        } else {
            return true;
        }
    }
}
