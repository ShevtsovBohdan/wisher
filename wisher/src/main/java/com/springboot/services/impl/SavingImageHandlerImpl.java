package com.springboot.services.impl;


import com.springboot.persistence.interfaces.ManageWish;
import com.springboot.services.interfaces.SavingImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;


/**
 * Class that used for saving files to local directory and file's path in the database
 */
@Component
public class SavingImageHandlerImpl implements SavingImageHandler {
    //TODO path for one PC
    public static final String LOCAL_PATH_OF_THE_IMAGE = "/home/bohdansh/pictures/resultImage";

    @Autowired
    private ManageWish manageWish;

    /**
     * Saves images to the local directory.
     *
     * @param byteArray image's byte array for saving it to the directory.
     * @param imageExtension sets extension of the image.
     * @return path to the image that was saved.
     * @throws IOException if an error occurs during image's reading or writing.
     */
    @Override
    public String saveImageToTheDirectory(byte[] byteArray, String imageExtension) throws IOException{

        BufferedImage resultImage = ImageIO.read(new ByteArrayInputStream(byteArray));

        String pathForSaving = LOCAL_PATH_OF_THE_IMAGE + new Date().getTime() + "." + imageExtension;

        ImageIO.write(resultImage, imageExtension, new File(pathForSaving));

        return pathForSaving;
    }

    /**
     * Saves path to the image in database.
     *
     * @param fileBytes image's byte array.
     * @param wishID ID of the wish to which image will be saved.
     * @param imageExtension extension of the image.
     * @param originalName original name of the image that was uploaded by user.
     * @throws IOException if an error occurs during image's reading or writing.
     */
    @Override
    public void saveImageLocationToDataBase(byte[] fileBytes, Integer wishID, String imageExtension, String originalName) throws IOException {
        manageWish.saveWishLocalLink(wishID, saveImageToTheDirectory(fileBytes, imageExtension));
        manageWish.saveOriginalImageWish(wishID, originalName);
    }

    /**
     * Gives commands for saving image.
     *
     * @param file sets image.
     * @param wishID sets ID of the wish to which image will be saved.
     * @throws IOException sets ID of the wish to which image will be saved.
     */
    @Override
    public void saveImageToTheDirectoryAndToTheDataBase(MultipartFile file, Integer wishID) throws IOException{
        saveImageLocationToDataBase(file.getBytes(), wishID,
                file.getOriginalFilename().split("\\.")[1],
                file.getOriginalFilename().split("\\.")[0]);

    }


}
