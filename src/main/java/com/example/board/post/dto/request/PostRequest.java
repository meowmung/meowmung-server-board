package com.example.board.post.dto.request;

import com.example.board.board.entity.Board;
import com.example.board.post.entity.Post;
import java.util.List;

public record PostRequest(
        String title,
        String content,
        List<String> imageUrls
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