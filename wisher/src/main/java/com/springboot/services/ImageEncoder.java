package com.springboot.services;


import com.springboot.persistence.interfaces.ManageWish;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;
import java.io.FileInputStream;

/**
 * Class that encodes image in Base64 format.
 */
public class ImageEncoder {

    @Autowired
    private ManageWish manageWish;

    /**
     * Gets path to the image from the database and returns encoded image or null if image wasn't found
     *
     * @param wishID ID of the wish for which image need to be encoded
     * @return encoded image or null if image wasn't found
     * @throws Exception if an I/O error occurs.
     */
    public String encode(Integer wishID) throws Exception {
        try {
            File file = new File(manageWish.getWishLocalLink(wishID));
            byte[] bytesArray = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytesArray);
            fis.close();

            String encodedImage = Base64.getEncoder().encodeToString(bytesArray);

            return encodedImage;
            //TODO catch only NullPointerException. Add or Exception
        } catch (NullPointerException e) {
            return null;
        }


    }
}
