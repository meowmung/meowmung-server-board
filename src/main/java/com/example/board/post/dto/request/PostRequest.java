package com.example.board.post.dto.request;

import com.example.board.board.entity.Board;
import com.example.board.post.entity.Post;

public record PostRequest(
        String title,
        String content
) {

    public Post toEntity(Board board) {
        return Post.builder()
                .title(title)
                .content(content)
//                .board((BoardCategory.fromString(boardCategory)))
                .board(board)
                .build();
    }

}