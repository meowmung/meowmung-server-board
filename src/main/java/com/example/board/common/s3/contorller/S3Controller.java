package com.example.board.common.s3.contorller;

import com.example.board.common.s3.dto.request.PreRequest;
import com.example.board.common.s3.service.S3Service;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class S3Controller {
    private final S3Service s3Service;

    // Presigned-url 발급 요청
    @PostMapping("/presigned-url")
    @ResponseBody
//    public String getPresignedUrl(@RequestParam String filename) {
    public List<String> getPresignedUrl(@RequestBody List<PreRequest> preRequest) {
        List<String> presignedUrls = new ArrayList<>();
        for (PreRequest pre : preRequest) {
            String presignedUrl = s3Service.createPresignedUrl("board_image/" + pre.name());
            presignedUrls.add(presignedUrl);
            System.out.println(presignedUrl);
        }

        return presignedUrls;
    }

}
