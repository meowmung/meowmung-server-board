package com.example.board.post.dto.request;

import com.example.board.common.BoardCategory;
import com.example.board.post.entity.Post;

public record PostRequest(
        String title,
        String content) {

    public Post toEntity(String board) {
        return Post.builder()
                .title(title)
                .content(content)
                .boardCategory(BoardCategory.valueOf(board.toUpperCase()))
                .build();
    }
}
