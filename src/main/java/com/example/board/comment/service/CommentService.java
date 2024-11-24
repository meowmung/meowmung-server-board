package com.example.board.comment.service;

import com.example.board.comment.dto.request.CommentRequest;
import com.example.board.comment.dto.response.CommentResponse;
import com.example.board.comment.entity.Comment;
import com.example.board.post.entity.Post;
import com.example.board.comment.repository.CommentRepository;
import com.example.board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentResponse saveComment(Long postId, CommentRequest commentRequest) {
        Comment comment = commentRequest.toEntity(findByPostId(postId));
        commentRepository.save(comment);
        return CommentResponse.fromEntity(comment);
    }

    public Post findByPostId(Long postId) {
        return postRepository.findByPostId(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new RuntimeException("해당 댓글은 존재하지 않습니다."));
        commentRepository.delete(comment);
    }

    // 신고 횟수 확인 하고 5가 되면 댓글 삭제
    @Transactional
    public void checkAndDeletePost(Long commentId) {
        Comment comment = commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new RuntimeException("해당 댓글은 존재하지 않습니다."));

        if (comment.getCommentComplainCount() >= 5) {
            commentRepository.delete(comment);
        }
    }

}
