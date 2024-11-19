package com.example.board.comment.dto.response;

import com.example.board.comment.entity.Comment;
import java.util.Date;

public record CommentResponse(
        Long commentId,
        String commentContent,
        Date createdAt,
        Long postId,
        String content

) {
    public Comment toEntity(Comment comment) {
        return Comment.builder()
                .

    }

}
