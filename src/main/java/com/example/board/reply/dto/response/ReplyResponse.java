package com.example.board.reply.dto.response;

import com.example.board.reply.entity.Reply;
import java.util.Date;

public record ReplyResponse(
        Long replyId,
        String replyContent,
        Date createdAt,
        Long commentId,
        String commentContent
) {
    public static ReplyResponse fromEntity(Reply reply) {
        return new ReplyResponse(
                reply.getReplyId(),
                reply.getReplyContent(),
                reply.getCreatedAt(),
                reply.getComment().getCommentId(),
                reply.getComment().getCommentContent()
//                reply.getComment()
        );
    }
}
