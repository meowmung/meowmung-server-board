package com.example.board.reply.mapper;

import com.example.board.reply.dto.response.ReplyResponse;
import com.example.board.reply.entity.Reply;
import org.springframework.stereotype.Component;

@Component
public class ReplyMapper {

    public ReplyResponse toResponse(Reply reply) {
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
