package com.example.board.controller;

import com.example.board.dto.request.PostEditRequest;
import com.example.board.dto.request.PostRequest;
import com.example.board.entity.Post;
import com.example.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("게시글이 삭제되었습니다.");
    }

    /*
        1. 수정할 게시글 id, 수정할 내용 받기
        2. 받은 id 게시글 찾기
        3. 기존 내용을 -> 받은 내용으로 수정
    */
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable(name = "id") Long id, @RequestBody PostEditRequest postEditRequest) {
        Post updatedPost = postService.updatePost(id, postEditRequest);
        return ResponseEntity.ok(updatedPost);
    }


}
