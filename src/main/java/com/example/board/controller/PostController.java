package com.example.board.controller;

import com.example.board.dto.request.PostRequest;
import com.example.board.entity.Post;
import com.example.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    /*
        게시글 생성
       1. 사용자가 작성한 내용을 받아서
       2. DB 에 저장
    */
    @PostMapping("/{boardCategory}")
    public ResponseEntity<Post> addPost(@PathVariable(name = "boardCategory") String board,
                                        @RequestBody PostRequest postRequest) {
        Post savedPost = postService.savePost(board, postRequest);
        System.out.println(savedPost.toString());
        return ResponseEntity.ok(savedPost);
    }
}
