package com.example.board.reply.dto.response;

import java.util.Date;

public record ReplyResponse(
        Long replyId,
        String replyContent,
        Date createdAt,
        Long commentId,
        String commentContent
) {
    // commentContent 까지 필요가 있을까
}
