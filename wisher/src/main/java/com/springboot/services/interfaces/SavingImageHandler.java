package com.springboot.services.interfaces;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SavingImageHandler {
    String saveImageToTheDirectory(byte[] byteArray, String imageExtension) throws IOException;
    void saveImageLocationToDataBase(byte[] byteArray, Integer wishID, String imageExtension, String originalName) throws IOException;
    void saveImageToTheDirectoryAndToTheDataBase(MultipartFile file, Integer wishID) throws IOException;
}
