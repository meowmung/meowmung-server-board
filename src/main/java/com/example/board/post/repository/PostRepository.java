package com.example.board.post.repository;

import com.example.board.post.entity.Post;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long postId);

    @Query("SELECT p FROM Post p "
            + "JOIN FETCH p.board b "
            + "LEFT JOIN FETCH p.comments c "
            + "WHERE b.boardCategory = :category")
    Page<Post> findByCategory(String category, Pageable pageable);

}
