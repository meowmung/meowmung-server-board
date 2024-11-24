package com.example.board.board.dto.response;

import com.example.board.board.entity.Board;
import com.example.board.post.dto.response.PostResponse;
import java.util.List;
import java.util.stream.Collectors;

public record BoardResponse (
        Long boardId,
        String boardCategory,
        List<PostResponse> posts
) {
    public static BoardResponse fromEntity(Board board) {
        // Board에 속한 Post들을 PostResponse 리스트로 변환
        List<PostResponse> postResponses = board.getPosts().stream()
                .map(post -> new PostResponse(
                        post.getPostId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getCreatedAt(),
                        post.getUpdatedAt(),
                        post.getViewCount(),
                        post.getPostComplainCount(),
                        post.getBoard().getBoardId(),
                        post.getBoard().getBoardCategory()
                ))
                .collect(Collectors.toList());

        // BoardResponse 반환
        return new BoardResponse(
                board.getBoardId(),
                board.getBoardCategory(),
                postResponses
        );
    }

}
