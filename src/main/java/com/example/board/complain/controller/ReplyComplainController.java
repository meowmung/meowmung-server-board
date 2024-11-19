package com.example.board.complain.controller;

import com.example.board.complain.dto.request.ReplyComplainRequest;
import com.example.board.complain.dto.response.ReplyComplainResponse;
import com.example.board.complain.service.ReplyComplainService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{boardCategory}/{postId}/comments/{commentId}/replies/{replyId}")
public class ReplyComplainController {
    private final ReplyComplainService replyComplainService;

    /*
        대댓글 신고 생성
    */
    @PostMapping("/complain")
    public ResponseEntity<ReplyComplainResponse> addReplyComplain(@PathVariable(name = "replyId") Long replyId,
                                                                    @RequestBody ReplyComplainRequest replyComplainRequest) {
        ReplyComplainResponse savedReplyComplain = replyComplainService.saveReplyComplain(replyId, replyComplainRequest);
        return ResponseEntity.ok(savedReplyComplain);
    }

    /*
        대댓글 아이디에 접수된 모든 신고 목록 조회
    */
    @GetMapping("/complain")
    public ResponseEntity<List<ReplyComplainResponse>> getReplyComplain(@PathVariable(name = "replyId") Long replyId) {
        List<ReplyComplainResponse> replies = replyComplainService.getAllReplyComplain(replyId);
        return ResponseEntity.ok(replies);
    }
}
