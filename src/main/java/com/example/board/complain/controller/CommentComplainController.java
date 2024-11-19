package com.example.board.complain.controller;

import com.example.board.complain.dto.request.CommentComplainRequest;
import com.example.board.complain.dto.response.CommentComplainResponse;
import com.example.board.complain.service.CommentComplainService;
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
@RequestMapping("/{boardCategory}/{postId}/comments/{commentId}")
public class CommentComplainController {
    private final CommentComplainService commentComplainService;

    /*
        댓글 신고 생성
    */
    @PostMapping("/complain")
    public ResponseEntity<CommentComplainResponse> addCommentComplain(@PathVariable(name = "commentId") Long commentId,
                                                               @RequestBody CommentComplainRequest commentComplainRequest) {
        CommentComplainResponse savedCommentComplain = commentComplainService.saveCommentComplain(commentId, commentComplainRequest);
        return ResponseEntity.ok(savedCommentComplain);
    }

    /*
        댓글아이디에 접수된 모든 신고 목록 조회
    */
    @GetMapping("/complain")
    public ResponseEntity<List<CommentComplainResponse>> getCommentComplain(@PathVariable(name = "commentId") Long commentId) {
        List<CommentComplainResponse> complains = commentComplainService.getAllCommentComplain(commentId);
        return ResponseEntity.ok(complains);
    }
}
