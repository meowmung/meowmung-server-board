package com.example.board.comment.service;

import com.example.board.comment.dto.request.CommentRequest;
import com.example.board.comment.dto.response.CommentResponse;
import com.example.board.comment.entity.Comment;
import com.example.board.post.entity.Post;
import com.example.board.comment.mapper.CommentMapper;
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
    private final CommentMapper commentMapper;

    /*
        댓글 등록
        1. commentRequest 를 담은 comment 만들어서
        2. 저장
    */
    public CommentResponse saveComment(Long postId, CommentRequest commentRequest) {
        Comment comment = commentRequest.toEntity(findByPostId(postId));
        commentRepository.save(comment);
        return commentMapper.toResponse(comment);
    }

    /*
        postId 로 게시글 찾기
    */
    public Post findByPostId(Long postId) {
        return postRepository.findByPostId(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
    }

    /*
        댓글 삭제
        1. commentId 에 해당하는 댓글 찾아서
        2. 삭제
    */
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new RuntimeException("해당 댓글은 존재하지 않습니다."));
        commentRepository.delete(comment);
    }

}
