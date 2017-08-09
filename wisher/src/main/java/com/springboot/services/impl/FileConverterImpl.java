package com.springboot.services.impl;


import com.springboot.services.interfaces.FileConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

/**
 * Class that used for encoding image in Base64 format.
 */
@Component
public class FileConverterImpl implements FileConverter {
    /**
     * Returns encoded image.
     *
     * @param file image that will be encoded.
     * @return encoded image.
     * @throws IOException in case of access errors (if the temporary store fails).
     */
    @Override
    public String toBase64(MultipartFile file) throws IOException{
        byte[] bytes = file.getBytes();
        String base64Bytes = Base64.getEncoder().encodeToString(bytes);
        return base64Bytes;
    }
}
