package com.example.board.s3.contorller;

import com.example.board.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;

    /*
        1. 요청 받아서
        2. presigned URL 발급
    */
    @GetMapping("/presigned-url")
    @ResponseBody
    public String getPresignedUrl(@RequestParam String filename) {
        String presignedUrl = s3Service.createPresignedUrl("test/" + filename);
        return presignedUrl;
    }
}
