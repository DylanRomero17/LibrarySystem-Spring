package com.dylandev.books.services;

import com.dylandev.books.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    Image uploadImage(MultipartFile file) throws IOException;

    void deleteImage(Image image) throws IOException;
}
