package com.example.board.complain.dto.request;

import com.example.board.complain.entity.ReplyComplain;
import com.example.board.reply.entity.Reply;

public record ReplyComplainRequest (
        String replyComplainContent
) {
    public ReplyComplain toEntity (Reply reply) {
        return ReplyComplain.builder()
                .replyComplainContent(replyComplainContent)
                .reply(reply)
                .build();
    }
}
