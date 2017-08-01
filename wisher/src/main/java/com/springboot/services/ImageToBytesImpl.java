package com.springboot.services;


import com.springboot.interfaces.ImageToBytes;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Component
public class ImageToBytesImpl implements ImageToBytes{
    @Override
    public String reformat(byte[] byteArray, Integer wishID) throws IOException{


        BufferedImage resultImage = ImageIO.read(new ByteArrayInputStream(byteArray));

        String localPath = "/home/bohdansh/pictures/resultImage" + wishID +".jpg";

        ImageIO.write(resultImage, "jpg", new File(localPath));

        return localPath;
    }
}
