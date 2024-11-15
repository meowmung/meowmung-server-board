package com.example.board.controller;

import com.example.board.dto.request.PostRequest;
import com.example.board.entity.Post;
import com.example.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{boardCategory}")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> addPost(@PathVariable(name = "boardCategory") String board,
                                        @RequestBody PostRequest postRequest) {
        Post savedPost = postService.savePost(board, postRequest);
        System.out.println(savedPost.toString());
        return ResponseEntity.ok(savedPost);
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable(name = "id") Long id) {
        return postService.findByPostId(id);
    }
}
