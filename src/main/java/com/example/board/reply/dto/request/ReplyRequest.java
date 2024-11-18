package com.example.board.reply.dto.request;

import com.example.board.comment.entity.Comment;
import com.example.board.reply.entity.Reply;

public record ReplyRequest(String replyContent) {

    public Reply toEntity(Comment comment) {
        return Reply.builder()
                .replyContent(replyContent)
                .comment(comment)
                .build();
    }

}
