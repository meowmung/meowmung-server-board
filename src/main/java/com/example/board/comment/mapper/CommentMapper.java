package com.example.board.comment.mapper;

import com.example.board.comment.dto.response.CommentResponse;
import com.example.board.comment.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentResponse toResponse(Comment comment) {
        return new CommentResponse(
                comment.getCommentId(),
                comment.getCommentContent(),
                comment.getCreatedAt(),
                comment.getPost().getPostId(),
                comment.getPost().getContent()
        );
    }
}
