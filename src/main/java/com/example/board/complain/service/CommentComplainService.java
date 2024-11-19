package com.example.board.complain.service;

import com.example.board.comment.entity.Comment;
import com.example.board.comment.repository.CommentRepository;
import com.example.board.comment.service.CommentService;
import com.example.board.complain.dto.request.CommentComplainRequest;
import com.example.board.complain.dto.response.CommentComplainResponse;
import com.example.board.complain.entity.CommentComplain;
import com.example.board.complain.repository.CommentComplainRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentComplainService {
    private final CommentComplainRepository commentComplainRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    public CommentComplainResponse saveCommentComplain(Long commentId, CommentComplainRequest commentComplainRequest) {
        CommentComplain commentComplain = commentComplainRequest.toEntity(findByCommentId(commentId));
        commentComplainRepository.save(commentComplain);
//        incrementComplainCount(commentId);
        incrementCommentComplainCount(commentId);
        commentService.checkAndDeletePost(commentId);
        return CommentComplainResponse.fromEntity(commentComplain);
    }

    public Comment findByCommentId(Long commentId) {
        return commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with id: " + commentId));
    }



//     신고 횟수 증가
//     신고 테이블에서 신고 횟수를 가지고 있는 경우
//    public void incrementPostComplainCount(PostComplain postComplain) {
//        postComplain.setPostComplainCount(postComplain.getPostComplainCount() + 1);
//        postComplainRepository.save(postComplain);
//    }

    // 게시글에서 신고 횟수를 가지고 있는 경우
    public void incrementCommentComplainCount(Long commentId) {
        Comment comment = findByCommentId(commentId);
        comment.setCommentComplainCount(comment.getCommentComplainCount() + 1);
        commentRepository.save(comment);
    }

}
