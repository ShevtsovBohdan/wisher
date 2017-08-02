package com.springboot.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component()
public class GetBytesFromImage {

    @Bean
    public ImageEncoder encoder(){
        ImageEncoder imageEncoder = new ImageEncoder();
        return imageEncoder;
    }
}
