package com.example.board.post.dto.request;

import com.example.board.board.entity.Board;
import com.example.board.post.entity.Post;

public record PostRequest(
        String title,
        String content
) {
    public Post toEntity(Board board, String nickname, Long memberId) {
        return Post.builder()
                .board(board)
                .title(title)
                .content(content)
                .nickname(nickname)
                .memberId(memberId)
                .build();
    }
}