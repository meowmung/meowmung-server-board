package com.example.board.reply.dto.response;

import com.example.board.reply.entity.Reply;
import java.util.Date;

public record ReplyResponse(
        String nickname,
        Long replyId,
        String replyContent,
        Date createdAt
) {

    public static ReplyResponse fromEntity(Reply reply) {
        return new ReplyResponse(
                reply.getNickname(),
                reply.getReplyId(),
                reply.getReplyContent(),
                reply.getCreatedAt()
        );
    }
}
