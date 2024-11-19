package com.example.board.post.service;

import com.example.board.post.dto.request.PostEditRequest;
import com.example.board.post.dto.request.PostRequest;
import com.example.board.post.dto.response.PostResponse;
import com.example.board.post.entity.Post;
import com.example.board.post.repository.PostRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
//    private final PostMapper postMapper;

    public PostResponse savePost(String board, PostRequest postRequest) {
        Post post = postRequest.toEntity(board);
        postRepository.save(post);
        return PostResponse.fromEntity(postRepository.save(post));
    }

    public Post findByPostId(Long postId) {
        Optional<Post> opt = postRepository.findByPostId(postId);
        return opt.orElseGet(() -> opt
                .orElseThrow(() -> new RuntimeException("Post not found")));
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException("해당 게시물은 존재하지 않습니다."));
        postRepository.delete(post);
    }

    public Post updatePost(Long postId, PostEditRequest postEditRequest) {
        // 함수 가져다 쓰는걸로 변경
        Post post = findByPostId(postId);
        post.update(postEditRequest.title(), postEditRequest.content());
        return postRepository.save(post);
    }

    public void incrementViewCount(Long postId) {
        Post post = findByPostId(postId);
        post.setViewCount(post.getViewCount() + 1);
        postRepository.save(post);
    }

    // 신고 횟수 확인 하고 5가 되면 게시글 삭제
    @Transactional
    public void checkAndDeletePost(Long postId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException("해당 게시물은 존재하지 않습니다."));

        if (post.getComplainCount() >= 5) {
            postRepository.delete(post);
        }
    }
}
