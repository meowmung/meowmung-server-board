package com.example.board.reply.service;

import com.example.board.reply.dto.request.ReplyRequest;
import com.example.board.reply.dto.response.ReplyResponse;
import com.example.board.comment.entity.Comment;
import com.example.board.reply.entity.Reply;
import com.example.board.reply.repository.ReplyRepository;
import com.example.board.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final CommentRepository commentRepository;
    private final ReplyRepository replyRepository;

    public ReplyResponse saveReply(Long commentId,
                                   ReplyRequest replyRequest,
                                   String nickname, Long memberId) {
        Reply reply = replyRequest.toEntity(findByCommentId(commentId), nickname, memberId);
        Reply save = replyRepository.save(reply);
        return ReplyResponse.fromEntity(save);
    }

    public Comment findByCommentId(Long commentId) {
        return commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with id: " + commentId));
    }

    @Transactional
    public ResponseEntity<?> deleteReply(Long replyId, Long memberId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new RuntimeException("해당 답글 존재하지 않습니다."));

        if (!reply.getMemberId().equals(memberId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("삭제 권한이 없습니다.");
        }
        replyRepository.delete(reply);
        return ResponseEntity.status(HttpStatus.OK).body("답글이 삭제되었습니다.");
    }

    // 신고 횟수 확인 하고 5가 되면 대댓글 삭제
    @Transactional
    public void checkAndDeleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new RuntimeException("해당 대댓글은 존재하지 않습니다."));

        if (reply.getReplyComplainCount() >= 5) {
            replyRepository.delete(reply);
        }
    }

}
