package com.example.board.complain.controller;

import com.example.board.complain.dto.request.ComplainRequest;
import com.example.board.complain.dto.response.ComplainResponse;
import com.example.board.complain.service.ComplainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{boardCategory}/{postId}")
public class ComplainController {
    private final ComplainService complainService;

    /*
        1. 사용자가 신고 사유를 선택하고 신고하기 버튼을 누르면
        2. 신고 사유를 저장하고
        3. 신고 횟수 +1
        4. 신고 횟수가 5가 되면 게시글을 삭제
    */

    // 신고 사유 저장
    @PostMapping("/complain")
    public ResponseEntity<ComplainResponse> addComplain(@PathVariable(name = "postId") Long postId,
                                                        @RequestBody ComplainRequest complainRequest) {
        ComplainResponse savedComplain = complainService.saveComplain(postId, complainRequest);
        return ResponseEntity.ok(savedComplain);
    }
}
