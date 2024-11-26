package com.example.board.reply.service;

import com.example.board.reply.dto.request.ReplyRequest;
import com.example.board.reply.dto.response.ReplyResponse;
import com.example.board.comment.entity.Comment;
import com.example.board.reply.entity.Reply;
import com.example.board.reply.repository.ReplyRepository;
import com.example.board.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final CommentRepository commentRepository;
    private final ReplyRepository replyRepository;

    public ReplyResponse saveReply(Long commentId,
                                   ReplyRequest replyRequest,
                                   String nickname) {
        Reply reply = replyRequest.toEntity(findByCommentId(commentId),nickname);
        replyRepository.save(reply);
        return ReplyResponse.fromEntity(reply);
    }

    public Comment findByCommentId(Long commentId) {
        return commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with id: " + commentId));
    }

    @Transactional
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findByReplyId(replyId)
                .orElseThrow(() -> new RuntimeException("해당 답글은 존재하지 않습니다."));
        replyRepository.delete(reply);
    }

    // 신고 횟수 확인 하고 5가 되면 대댓글 삭제
    @Transactional
    public void checkAndDeleteReply(Long replyId) {
        Reply reply = replyRepository.findByReplyId(replyId)
                .orElseThrow(() -> new RuntimeException("해당 대댓글은 존재하지 않습니다."));

        if (reply.getReplyComplainCount() >= 5) {
            replyRepository.delete(reply);
        }
    }

}
