package com.example.board.comment.dto.response;

import java.util.Date;

public record CommentResponse(
        Long commentId,
        String commentContent,
        Date createdAt,
        Long postId,
        String content

        // 게시글 제목, 내용까지 필요할까
) {

}
