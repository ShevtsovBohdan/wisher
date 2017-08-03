package com.springboot.interfaces;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileConverter {
    public String toBase64(MultipartFile file) throws IOException;
}
