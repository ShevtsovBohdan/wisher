package com.springboot.services;


import com.springboot.interfaces.ManageWish;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.IOUtils;

import javax.imageio.ImageIO;
import javax.persistence.Convert;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ImageEncoder {

    @Autowired
    private ManageWish manageWish;

    public String encode(Integer wishID) throws Exception {
        try {

            File file = new File(manageWish.getWishLocalLink(wishID));
            byte[] bytesArray = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytesArray);
            fis.close();

            String encodedImage = java.util.Base64.getEncoder().encodeToString(bytesArray);

            return encodedImage;
        } catch (NullPointerException e) {
            return null;
        }


    }
}
