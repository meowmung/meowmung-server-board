package com.example.board.complain.service;

import com.example.board.complain.dto.request.ReplyComplainRequest;
import com.example.board.complain.dto.response.ReplyComplainResponse;
import com.example.board.complain.entity.ReplyComplain;
import com.example.board.complain.repository.ReplyComplainRepository;
import com.example.board.reply.entity.Reply;
import com.example.board.reply.repository.ReplyRepository;
import com.example.board.reply.service.ReplyService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyComplainService {
    private final ReplyComplainRepository replyComplainRepository;
    private final ReplyService replyService;
    private final ReplyRepository replyRepository;

    /*
        대댓글 신고 생성
    */
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

    /*
        특정 대댓글에 대한 모든 신고 내역 가져오기
    */
    public List<ReplyComplainResponse> getAllReplyComplain(Long replyId) {
        Reply reply = findByReplyId(replyId);
        List<ReplyComplain> getComplains = reply.getReplyComplains();
        return getComplains.stream()
                .map(ReplyComplainResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /*
        신고 횟수 증가
    */
    public void incrementReplyComplainCount(Long replyId) {
        Reply reply = findByReplyId(replyId);
        reply.setReplyComplainCount(reply.getReplyComplainCount() + 1);
        replyRepository.save(reply);
    }

//     신고 횟수 증가
//    public void incrementPostComplainCount(PostComplain postComplain) {
//        postComplain.setPostComplainCount(postComplain.getPostComplainCount() + 1);
//        postComplainRepository.save(postComplain);
//    }

}
