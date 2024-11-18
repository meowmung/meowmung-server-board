package com.example.board.comment.dto.request;

import com.example.board.comment.entity.Comment;
import com.example.board.post.entity.Post;

public record CommentRequest (String commentContent) {

    public Comment toEntity(Post post) {
        return Comment.builder()
                .commentContent(commentContent)
                .post(post)
                .build();
    }

}