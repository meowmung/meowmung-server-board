package com.example.board.complain.repository;

import com.example.board.complain.entity.CommentComplain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentComplainRepository extends JpaRepository<CommentComplain, Long> {
}
