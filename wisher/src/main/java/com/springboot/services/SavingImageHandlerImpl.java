package com.springboot.services;


import com.springboot.interfaces.ManageWish;
import com.springboot.interfaces.SavingImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Component
public class SavingImageHandlerImpl implements SavingImageHandler {
    public static final String LOCAL_PATH_OF_THE_IMAGE = "/home/bohdansh/pictures/resultImage";

    @Autowired
    private ManageWish manageWish;

    @Override
    public String saveImageToTheDirectory(byte[] byteArray, Integer wishID, String imageExtension) throws IOException{

        BufferedImage resultImage = ImageIO.read(new ByteArrayInputStream(byteArray));

        String pathForSaving = LOCAL_PATH_OF_THE_IMAGE + new Date().getTime() + "." + imageExtension;

        ImageIO.write(resultImage, imageExtension, new File(pathForSaving));

        return pathForSaving;
    }

    @Override
    public void saveImageLocationToDataBase(byte[] fileBytes, Integer wishID, String imageExtension, String originalName) throws IOException {
        manageWish.saveWishLocalLink(wishID, saveImageToTheDirectory(fileBytes, wishID, imageExtension));
        manageWish.saveOriginalImageWish(wishID, originalName);
    }

    @Override
    public void saveImageToTheDirectoryAndToTheDataBase(MultipartFile file, Integer wishID) throws IOException{
        saveImageLocationToDataBase(file.getBytes(), wishID,
                file.getOriginalFilename().split("\\.")[1],
                file.getOriginalFilename().split("\\.")[0]);

    }


}
