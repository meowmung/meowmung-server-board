package com.example.board.board.service;

import com.example.board.board.dto.response.BoardResponse;
import com.example.board.post.entity.Post;
import com.example.board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final PostRepository postRepository;

    public BoardResponse getPostsByPage(String boardCategory, Pageable pageable) {
        Page<Post> byCategory = postRepository.findByCategory(boardCategory, pageable);
        System.out.println(byCategory.getTotalPages());
        return BoardResponse.fromEntity(boardCategory, byCategory);
    }
}
