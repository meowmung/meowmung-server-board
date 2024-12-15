package com.example.board.complain.dto.request;

import com.example.board.comment.entity.Comment;
import com.example.board.complain.entity.CommentComplain;

public record CommentComplainRequest (
        Long commentComplainId
) {
    public static CommentComplain toEntity(Comment comment){
        return CommentComplain.builder()
//                .commentComplainContent(commentComplainContent)
                .comment(comment)
                .build();
    }
}
