package com.example.board.post.repository;

import com.example.board.post.entity.Post;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long postId);
//    int deleteByPostId(Long id);
}
