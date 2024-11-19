package com.example.board.complain.dto.request;

import com.example.board.comment.entity.Comment;
import com.example.board.complain.entity.CommentComplain;

public record CommentComplainRequest (
        String commentComplainContent
) {
    public CommentComplain toEntity (Comment comment){
        return CommentComplain.builder()
                .commentComplainContent(commentComplainContent)
                .comment(comment)
                .build();
    }
}
