package com.springboot.interfaces;


import java.io.IOException;

public interface ImageToBytes {
    public String reformat(byte[] byteArray, Integer wishID) throws IOException;
}
