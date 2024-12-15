package com.example.board.board.dto.response;

import com.example.board.post.dto.response.PostResponse;
import com.example.board.post.entity.Post;
import java.util.List;
import org.springframework.data.domain.Page;

public record BoardResponse(
        String boardCategory,
        List<PostResponse> postResponses,
        int totalPages
) {
    public static BoardResponse fromEntity(String boardCategory, Page<Post> byCategory) {
        // Page<Post> 데이터를 List<PostResponse>로 변환
        List<PostResponse> postResponses = byCategory.getContent().stream()
                .map(PostResponse::fromEntity) // Post를 PostResponse로 변환
                .toList();

        // BoardResponse 반환
        return new BoardResponse(
                boardCategory,
                postResponses,
                byCategory.getTotalPages()
        );
    }

}
