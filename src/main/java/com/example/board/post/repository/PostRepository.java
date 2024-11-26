package com.example.board.post.repository;

import com.example.board.board.entity.Board;
import com.example.board.post.entity.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long postId);
//    int deleteByPostId(Long id);
    List<Optional<Post>> findAllByBoard(Board board);

//    Optional<Post> findByPostIdAndBoardMat
}
