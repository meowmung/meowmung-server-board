package com.example.board.complain.controller;

import com.example.board.complain.dto.request.PostComplainRequest;
import com.example.board.complain.dto.response.PostComplainResponse;
import com.example.board.complain.service.PostComplainService;
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
public class PostComplainController {
    private final PostComplainService postComplainService;

    /*
        1. 사용자가 신고 사유를 선택하고 신고하기 버튼을 누르면
        2. 신고 사유를 저장하고
        3. 신고 횟수 +1
        4. 신고 횟수가 5가 되면 게시글을 삭제
    */

    // 신고 사유 저장
    @PostMapping("/complain")
    public ResponseEntity<PostComplainResponse> addComplain(@PathVariable(name = "postId") Long postId,
                                                            @RequestBody PostComplainRequest postComplainRequest) {
        PostComplainResponse savedComplain = postComplainService.saveComplain(postId, postComplainRequest);
        return ResponseEntity.ok(savedComplain);
    }
}
