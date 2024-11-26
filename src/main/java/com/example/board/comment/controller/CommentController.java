package com.example.board.comment.controller;

import com.example.board.comment.dto.request.CommentRequest;
import com.example.board.comment.dto.response.CommentResponse;
import com.example.board.comment.service.CommentService;
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
@RequestMapping("boards/{boardCategory}/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> addComment(@PathVariable(name = "postId") Long postId,
                                                      @RequestBody CommentRequest commentRequest,
                                                      @RequestHeader("X-Authorization-nickname") String nickname) {
        CommentResponse PostOneRequest = commentService.saveComment(postId, commentRequest, nickname);
        return ResponseEntity.ok(savedComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }
}
