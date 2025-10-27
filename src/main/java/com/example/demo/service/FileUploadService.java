package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final String uploadDir = "/home/ec2-user/deploy/static/images/";

    public String saveImage(MultipartFile file) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        File dest = new File(uploadDir + fileName);

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException("이미지 저장 실패: " + e.getMessage());
        }

        return "/static/images/" + fileName;
    }
}
