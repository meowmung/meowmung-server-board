package com.example.board.reply.dto.request;

import com.example.board.reply.entity.Reply;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public record ReplyOneRequest(
        Long replyId,
        String replyContent,
        String nickname,
        Date createdAt
) {
    public static List<ReplyOneRequest> fromEntity(List<Reply> replies) {
        return replies.stream()
                .map(reply -> new ReplyOneRequest(
                        reply.getReplyId(), // Reply 엔티티의 ID
                        reply.getReplyContent(), // 답글 내용
                        reply.getNickname(), // 작성자 닉네임
                        reply.getCreatedAt() // 작성일
                ))
                .collect(Collectors.toList());
    }
}

