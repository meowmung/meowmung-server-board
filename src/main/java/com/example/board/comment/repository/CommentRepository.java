package com.example.board.comment.repository;

import com.example.board.comment.entity.Comment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCommentId(Long commentId);
}
