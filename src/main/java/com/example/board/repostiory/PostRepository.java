package com.example.board.repostiory;

import com.example.board.entity.Post;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long id);
//    int deleteByPostId(Long id);
}
