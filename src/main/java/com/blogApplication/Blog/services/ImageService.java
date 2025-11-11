package com.blogApplication.Blog.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogApplication.Blog.exceptions.FileSaveErrorException;


@Service
public class ImageService {
    private static final String UPLOAD_DIR = "src/main/resources/static/images";

    public String saveFile(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);

            }
            String fileName = System.currentTimeMillis() + "" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);
            return fileName;
        } catch (IOException e) {
            throw new FileSaveErrorException("File save problems");
        }
    }

    public List<String> saveFiles(List<MultipartFile> files) {
        List<String> images = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = saveFile(file);
            images.add(fileName);
        }
        return images;
    }
}
