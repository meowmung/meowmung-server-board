package com.example.board.board.dto.response;

import com.example.board.board.entity.Board;
import com.example.board.post.dto.response.PostResponse;
import com.example.board.post.entity.Post;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record BoardResponse(
        String boardCategory,
        List<PostResponse> postResponses
) {
    public static BoardResponse fromEntity(String boardCategory, List<Optional<Post>> posts) {
        // Board에 속한 Post들을 PostResponse 리스트로 변환
        List<PostResponse> postResponses = posts.stream()
                .filter(Optional::isPresent) // Optional 값이 존재하는 경우에만 처리
                .map(Optional::get)         // Optional에서 Post 객체 추출
                .map(PostResponse::fromEntity) // Post를 PostResponse로 변환
                .toList();
        // BoardResponse 반환
        return new BoardResponse(
                boardCategory,
                postResponses
        );
    }

}
