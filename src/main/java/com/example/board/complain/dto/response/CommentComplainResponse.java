package com.example.board.complain.dto.response;

import com.example.board.complain.entity.CommentComplain;
import java.util.Date;

public record CommentComplainResponse(
        Long commentComplainId,
        String commentComplainContent,
        Date createdAt,

        // 신고한 댓글
        Long commentId,
        String commentContent,

        // 신고한 댓글의 상위 게시글
        Long postId,
        String title,
        String content
) {
    public static CommentComplainResponse fromEntity(CommentComplain commentComplain) {
        return new CommentComplainResponse(
                commentComplain.getCommentComplainId(),
                commentComplain.getCommentComplainContent(),
                commentComplain.getCreatedAt(),

                commentComplain.getComment().getCommentId(),
                commentComplain.getComment().getCommentContent(),

                commentComplain.getComment().getPost().getPostId(),
                commentComplain.getComment().getPost().getTitle(),
                commentComplain.getComment().getPost().getContent()
        );
    }
}
