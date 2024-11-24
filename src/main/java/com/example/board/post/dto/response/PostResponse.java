package com.example.board.post.dto.response;

import com.example.board.post.entity.Post;
import java.util.Date;

public record PostResponse(
        Long postId,
        String title,
        String content,
        Date createdAt,
        Date updatedAt,
        Integer viewCount,
        Integer postComplainCount,
        Long boardId,
        String boardCategory
) {
    public static PostResponse fromEntity(Post post) {
        return new PostResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getViewCount(),
                post.getPostComplainCount(),

                post.getBoard().getBoardId(),
                post.getBoard().getBoardCategory()
        );
    }


}
