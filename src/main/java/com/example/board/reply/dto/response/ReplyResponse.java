package com.example.board.reply.dto.response;

import com.example.board.reply.entity.Reply;
import java.util.Date;

public record ReplyResponse(
        Long replyId,
        String replyContent,
        Date createdAt,

        // 대댓글 신고 횟수
        Integer ReplyComplainCount,

        // 대댓글 상위 댓글
        Long commentId,
        String commentContent
) {

    public static ReplyResponse fromEntity(Reply reply) {
        return new ReplyResponse(
                reply.getReplyId(),
                reply.getReplyContent(),
                reply.getCreatedAt(),

                reply.getReplyComplainCount(),

                reply.getComment().getCommentId(),
                reply.getComment().getCommentContent()
        );
    }
}
