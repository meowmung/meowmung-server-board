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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{boardCategory}/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    /*
        댓글 등록
        1. 댓글을 등록할 postId 랑 commentRequest (댓글 내용) 필요
    */
    @PostMapping
    public ResponseEntity<CommentResponse> addComment(@PathVariable(name = "postId") Long postId,
                                                      @RequestBody CommentRequest commentRequest) {
        CommentResponse savedComment = commentService.saveComment(postId, commentRequest);
        return ResponseEntity.ok(savedComment);
    }

    /*
        댓글 삭제
        1. url 의 commentId 받아서
        2. service 의 삭제 메소드 호출
    */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }
}
