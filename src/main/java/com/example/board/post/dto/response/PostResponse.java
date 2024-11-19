package com.example.board.post.dto.response;

import com.example.board.common.BoardCategory;
import com.example.board.post.entity.Post;
import java.util.Date;

public record PostResponse(
        Long postId,
        String title,
        String content,
        BoardCategory boardCategory,
        Date createdAt,
        Date updatedAt,
        Integer viewCount,
        Integer postComplainCount
) {
    public static PostResponse fromEntity(Post post) {
        return new PostResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getBoardCategory(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getViewCount(),
                post.getPostComplainCount()
        );
    }
}
