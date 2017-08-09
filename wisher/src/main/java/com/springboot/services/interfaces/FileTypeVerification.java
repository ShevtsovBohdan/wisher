package com.springboot.services.interfaces;


import org.springframework.web.multipart.MultipartFile;

public interface FileTypeVerification {
    public boolean checkFileExtension(MultipartFile file);
}
