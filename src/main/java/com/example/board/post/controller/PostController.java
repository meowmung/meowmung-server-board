package com.example.board.post.controller;

import com.example.board.post.dto.request.PostEditRequest;
import com.example.board.post.dto.request.PostOneRequest;
import com.example.board.post.dto.request.PostRequest;
import com.example.board.post.entity.Post;
import com.example.board.post.service.PostService;
import java.net.URLDecoder;
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

    // 게시글 조회
    @GetMapping("/{postId}")
    public PostOneRequest getPost(@PathVariable(name = "postId") Long postId) {
        return postService.getPost(postId);
    }

    // 게시글 생성
    @PostMapping
    public PostOneRequest addPost(@PathVariable(name = "boardCategory") String boardCategory,
                                  @RequestBody PostRequest postRequest,
                                  @RequestHeader("X-Authorization-email") String email,
                                  @RequestHeader("X-Authorization-nickname") String nickname,
                                  @RequestHeader("X-Authorization-memberId") Long memberId) {

        nickname = URLDecoder.decode(nickname);
        return postService.savePost(boardCategory, postRequest, nickname, memberId);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "postId") Long postId) {
        if (postId == null) {
            return ResponseEntity.badRequest().body("postId가 유효하지 않습니다.");
        }

        postService.deletePost(postId);
        return ResponseEntity.ok("게시글이 삭제되었습니다.");
    }

    // 게시글 수정
    @PutMapping("/{postid}")
    public ResponseEntity<Post> updatePost(@PathVariable(name = "postid") Long postId,
                                           @RequestBody PostEditRequest postEditRequest) {

        Post updatedPost = postService.updatePost(postId, postEditRequest);
        return ResponseEntity.ok(updatedPost);
    }

}
