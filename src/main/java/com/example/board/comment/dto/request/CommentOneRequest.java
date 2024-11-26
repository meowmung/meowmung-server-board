package com.example.board.comment.dto.request;

import com.example.board.comment.entity.Comment;
import com.example.board.reply.dto.request.ReplyOneRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public record CommentOneRequest (
        Long commentId,
        String commentContent,
        String nickname,
        Date createdAt,
        List<ReplyOneRequest> requests
) {
    public static List<CommentOneRequest> fromEntity (List<Comment> comments) {
        return comments.stream()
                .map(comment -> new CommentOneRequest(
                        comment.getCommentId(), // Comment 엔티티의 ID
                        comment.getCommentContent(), // 댓글 내용
                        comment.getNickname(), // 작성자 닉네임
                        comment.getCreatedAt(), // 작성일
                        ReplyOneRequest.fromEntity(comment.getReplies()) // 댓글의 답글을 변환
                ))
                .collect(Collectors.toList());
    }
}