package com.example.board.complain.service;

import com.example.board.complain.dto.request.ComplainRequest;
import com.example.board.complain.dto.response.ComplainResponse;
import com.example.board.complain.entity.Complain;
import com.example.board.post.entity.Post;
import com.example.board.post.service.PostService;
import com.example.board.complain.repository.ComplainRepository;
import com.example.board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplainService {
    private final ComplainRepository complainRepository;
    private final PostRepository postRepository;
    private final PostService postService;

    public ComplainResponse saveComplain(Long postId, ComplainRequest complainRequest) {
        Complain complain = complainRequest.toEntity(findByPostId(postId));
        complainRepository.save(complain);
        incrementComplainCount(complain);
        incrementPostComplainCount(postId);
        postService.checkAndDeletePost(postId);
        return ComplainResponse.fromEntity(complain);
    }

    public Post findByPostId(Long postId) {
        return postRepository.findByPostId(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
    }

    // 신고 횟수 증가
    // 신고 테이블에서 신고 횟수를 가지고 있는 경우
    public void incrementComplainCount(Complain complain) {
        complain.setComplainCount(complain.getComplainCount() + 1);
        complainRepository.save(complain);
    }

    // 게시글에서 신고 횟수를 가지고 있는 경우
    public void incrementPostComplainCount(Long postId) {
        Post post = findByPostId(postId);
        post.setComplainCount(post.getComplainCount() + 1);
        postRepository.save(post);
    }

}
