package com.springboot.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class that user for creation ImageEncoder class.
 */
@Configuration
//TODO move to config package
public class GetBytesFromImage {

    /**
     * Returns ImageEncoder instance.
     *
     * @return ImageEncoder instance.
     */
    @Bean
    public ImageEncoder encoder(){
        ImageEncoder imageEncoder = new ImageEncoder();
        return imageEncoder;
    }
}
