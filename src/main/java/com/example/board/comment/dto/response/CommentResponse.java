package com.example.board.comment.dto.response;

import com.example.board.comment.entity.Comment;
import java.util.Date;

public record CommentResponse(
        Long commentId,
        String commentContent,
        Date createdAt,

        // 댓글 신고 횟수
        Integer commentComplainCount,

        // 댓글 상위 게시글
        Long postId,
        String title,
        String content
) {

    public static CommentResponse fromEntity(Comment comment) {
        return new CommentResponse(
                comment.getCommentId(),
                comment.getCommentContent(),
                comment.getCreatedAt(),

                comment.getCommentComplainCount(),

                comment.getPost().getPostId(),
                comment.getPost().getTitle(),
                comment.getPost().getContent()


        );
    }
}
