package com.example.board.service;

import com.example.board.dto.request.PostRequest;
import com.example.board.entity.Post;
import com.example.board.repostiory.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post savePost(PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.title());
        post.setContent(postRequest.content());
        return postRepository.save(post);
    }
}
