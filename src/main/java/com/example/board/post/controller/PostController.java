package com.example.board.post.controller;

import com.example.board.post.dto.request.PostEditRequest;
import com.example.board.post.dto.request.PostRequest;
import com.example.board.post.dto.response.PostResponse;
import com.example.board.post.entity.Post;
import com.example.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("boards/{boardCategory}")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    // 현재 사용자 정체
    @GetMapping("/hi")
    public void hi(@PathVariable(name = "boardCategory") String boardCategory,
                   @RequestHeader("X-Authorization-email") String email,
                   @RequestHeader("X-Authorization-nickname") String nickname){
        System.out.println(boardCategory);
        System.out.println(email);
        System.out.println(nickname);
    }

    @PostMapping
    public ResponseEntity<PostResponse> addPost(@PathVariable(name = "boardCategory") String boardCategory,
                                                @RequestBody PostRequest postRequest) {
        PostResponse savedPost = postService.savePost(boardCategory, postRequest);
        return ResponseEntity.ok(savedPost);
    }

    @GetMapping("/{postid}")
    public Post getPost (@PathVariable(name = "postid") Long postId) {
        postService.incrementViewCount(postId);
        return postService.findByPostId(postId);
    }

    @DeleteMapping("/{postid}")
    public ResponseEntity<String> deletePost (@PathVariable(name = "postid") Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("게시글이 삭제되었습니다.");
    }

    @PutMapping("/{postid}")
    public ResponseEntity<Post> updatePost (@PathVariable(name = "postid") Long postId, @RequestBody PostEditRequest postEditRequest) {
        Post updatedPost = postService.updatePost(postId, postEditRequest);
        return ResponseEntity.ok(updatedPost);
    }
}
