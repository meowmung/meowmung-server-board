package com.example.board.complain.dto.response;

import com.example.board.complain.entity.CommentComplain;
import com.example.board.complain.entity.ReplyComplain;
import java.util.Date;

public record ReplyComplainResponse (
        Long replyComplainId,
        String replyComplainContent,
        Date createdAt,

        // 신고한 대댓글
        Long replyId,
        String replyContent,

        // 신고한 대댓글의 상위 댓글
        Long commentId,
        String commentContent,

        // 신고한 대댓글의 상위 게시글
        Long postId,
        String title,
        String content
) {
    public static ReplyComplainResponse fromEntity(ReplyComplain replyComplain) {
        return new ReplyComplainResponse(
                replyComplain.getReplyComplainId(),
                replyComplain.getReplyComplainContent(),
                replyComplain.getCreatedAt(),

                replyComplain.getReply().getReplyId(),
                replyComplain.getReply().getReplyContent(),

                replyComplain.getReply().getComment().getCommentId(),
                replyComplain.getReply().getComment().getCommentContent(),

                replyComplain.getReply().getPost().getPostId(),
                replyComplain.getReply().getPost().getTitle(),
                replyComplain.getReply().getPost().getContent()

        );
    }
}
