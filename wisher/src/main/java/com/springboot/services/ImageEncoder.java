package com.springboot.services;


import com.springboot.interfaces.ManageWish;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class ImageEncoder {

    @Autowired
    private ManageWish manageWish;

    public String encode(Integer wishID) throws Exception {

        try {
            BufferedImage image = ImageIO.read(new File(manageWish.getWishLocalLink(wishID)));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            byte[] res = baos.toByteArray();
            byte[] encodedImage = java.util.Base64.getEncoder().encode(baos.toByteArray());
            return "data:image/jpg;base64, " + encodedImage;
        } catch (NullPointerException e) {
            return "#";
        }


    }
}
