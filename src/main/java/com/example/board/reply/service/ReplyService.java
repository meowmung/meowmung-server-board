package com.example.board.reply.service;

import com.example.board.reply.dto.request.ReplyRequest;
import com.example.board.reply.dto.response.ReplyResponse;
import com.example.board.comment.entity.Comment;
import com.example.board.reply.entity.Reply;
import com.example.board.reply.mapper.ReplyMapper;
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
    private final ReplyMapper replyMapper;

    public ReplyResponse saveCommentReply(Long commentId,
                                          ReplyRequest replyRequest) {
        Reply reply = replyRequest.toEntity(findByCommentId(commentId));
        replyRepository.save(reply);
        return replyMapper.toResponse(reply);
    }

    public Comment findByCommentId(Long commentId) {
        return commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + commentId));
    }

    @Transactional
    public void deleteReplyComment(Long replyId) {
        Reply reply = replyRepository.findByReplyId(replyId)
                .orElseThrow(() -> new RuntimeException("해당 답글은 존재하지 않습니다."));
        replyRepository.delete(reply);
    }

}
