package com.example.board.complain.service;

import com.example.board.comment.entity.Comment;
import com.example.board.complain.dto.request.CommentComplainRequest;
import com.example.board.complain.dto.request.ReplyComplainRequest;
import com.example.board.complain.dto.response.CommentComplainResponse;
import com.example.board.complain.dto.response.ReplyComplainResponse;
import com.example.board.complain.entity.CommentComplain;
import com.example.board.complain.entity.ReplyComplain;
import com.example.board.complain.repository.ReplyComplainRepository;
import com.example.board.reply.entity.Reply;
import com.example.board.reply.repository.ReplyRepository;
import com.example.board.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyComplainService {
    private final ReplyComplainRepository replyComplainRepository;
    private final ReplyService replyService;
    private final ReplyRepository replyRepository;

    public ReplyComplainResponse saveReplyComplain(Long replyId, ReplyComplainRequest replyComplainRequest) {
        ReplyComplain replyComplain = replyComplainRequest.toEntity(findByReplyId(replyId));
        replyComplainRepository.save(replyComplain);
//        incrementReplyCount(replyComplain);
        incrementReplyComplainCount(replyId);
        replyService.checkAndDeleteReply(replyId);
        return ReplyComplainResponse.fromEntity(replyComplain);
    }

    public Reply findByReplyId(Long replyId) {
        return replyRepository.findByReplyId(replyId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with id: " + replyId));
    }

    // 게시글에서 신고 횟수를 가지고 있는 경우
    public void incrementReplyComplainCount(Long replyId) {
        Reply reply = findByReplyId(replyId);
        reply.setReplyComplainCount(reply.getReplyComplainCount() + 1);
        replyRepository.save(reply);
    }

//     신고 횟수 증가
//     신고 테이블에서 신고 횟수를 가지고 있는 경우
//    public void incrementPostComplainCount(PostComplain postComplain) {
//        postComplain.setPostComplainCount(postComplain.getPostComplainCount() + 1);
//        postComplainRepository.save(postComplain);
//    }

}
