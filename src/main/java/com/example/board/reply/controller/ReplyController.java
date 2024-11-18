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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{boardCategory}/{postId}/comments/{commentId}/replies")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<ReplyResponse> addCommentReply(@PathVariable(name = "commentId") Long commentId,
                                                         @RequestBody ReplyRequest replyRequest) {
        ReplyResponse savedCommentReply = replyService.saveCommentReply(commentId, replyRequest);
        return ResponseEntity.ok(savedCommentReply);
    }

    @DeleteMapping("/{replyId}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "replyId") Long replyId) {
        replyService.deleteReplyComment(replyId);
        return ResponseEntity.ok("답글이 삭제되었습니다.");
    }
}
