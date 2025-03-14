package com.example.board.comment.dto.response;

import com.example.board.comment.entity.Comment;
import java.util.Date;

public record CommentResponse(
        String nickname,
        Long commentId,
        String commentContent,
        Date createdAt
) {

    public static CommentResponse fromEntity(Comment comment) {
        return new CommentResponse(
                comment.getNickname(),
                comment.getCommentId(),
                comment.getCommentContent(),
                comment.getCreatedAt()
        );
    }
}
