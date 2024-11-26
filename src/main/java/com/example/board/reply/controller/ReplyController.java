package com.example.board.reply.controller;

import com.example.board.reply.dto.request.ReplyRequest;
import com.example.board.reply.dto.response.ReplyResponse;
import com.example.board.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("boards/{boardCategory}/{postId}/comments/{commentId}/replies")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<ReplyResponse> addReply(@PathVariable(name = "commentId") Long commentId,
                                                  @PathVariable(name = "postId") Long postId,
                                                  @RequestBody ReplyRequest replyRequest,
                                                  @RequestHeader("X-Authorization-nickname") String nickname) {
        ReplyResponse savedReply = replyService.saveReply(commentId, replyRequest, nickname);
        return ResponseEntity.ok(savedReply);
    }

    @DeleteMapping("/{replyId}")
    public ResponseEntity<String> deleteReply(@PathVariable(name = "replyId") Long replyId) {
        replyService.deleteReply(replyId);
        return ResponseEntity.ok("대댓글이 삭제되었습니다.");
    }
}
