package com.example.board.controller;

import com.example.board.dto.request.PostRequest;
import com.example.board.entity.Post;
import com.example.board.repostiory.PostRepository;
import com.example.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    /*
        게시글 생성
       1. 사용자가 작성한 내용을 받아서
       2. DB 에 저장
    */
    @PostMapping("/free")
    public ResponseEntity<Post> addPost(@RequestBody PostRequest postRequest) {
        Post savedPost = postService.savePost(postRequest);
        System.out.println(savedPost.toString());
        return ResponseEntity.ok(savedPost);
    }
}
