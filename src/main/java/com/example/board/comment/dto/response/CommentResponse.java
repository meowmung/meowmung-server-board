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
    public static CommentResponse fromEntity(Comment comment) {
        return new CommentResponse(
                comment.getCommentId(),
                comment.getCommentContent(),
                comment.getCreatedAt(),
                comment.getPost().getPostId(),
                comment.getPost().getContent()
        );
    }
}
