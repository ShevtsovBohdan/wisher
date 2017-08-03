package com.springboot.services;


import com.springboot.interfaces.FileConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;


@Component
public class FileConverterImpl implements FileConverter{
    @Override
    public String toBase64(MultipartFile file) throws IOException{
        byte[] bytes = file.getBytes();
        String base64Bytes = Base64.getEncoder().encodeToString(bytes);
        return base64Bytes;
    }
}
