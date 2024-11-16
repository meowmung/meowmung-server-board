package com.example.board.service;

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

    public Post findByPostId(Long id) {
        Optional<Post> opt = postRepository.findByPostId(id);
        return opt.orElseGet(() -> opt
                .orElseThrow(() -> new RuntimeException("Post not found")));
    }

    @Transactional
    public int deletePost(Long id) {
        if (!existDeletePost(id)) {
            throw new RuntimeException("해당 게시물은 존재하지 않습니다.");
        }
        return postRepository.deleteByPostId(id);
    }

    public boolean existDeletePost(Long id) {
        return postRepository.existsById(id);
    }
}
