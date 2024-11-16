package com.example.board.service;

import com.example.board.dto.request.PostEditRequest;
import com.example.board.dto.request.PostRequest;
import com.example.board.entity.Post;
import com.example.board.repostiory.PostRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post savePost(String board, PostRequest postRequest) {
        Post post = postRequest.toEntity(board);
        return postRepository.save(post);
    }

    public Post findByPostId(Long postId) {
        Optional<Post> opt = postRepository.findByPostId(postId);
        return opt.orElseGet(() -> opt
                .orElseThrow(() -> new RuntimeException("Post not found")));
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("해당 게시물은 존재하지 않습니다."));
        postRepository.delete(post);
    }

    public Post updatePost(Long postId, PostEditRequest postEditRequest) {
        // 함수 가져다 쓰는걸로 변경
        Post post = findByPostId(postId);
        post.update(postEditRequest.title(), postEditRequest.content());
        return postRepository.save(post);
    }
}
