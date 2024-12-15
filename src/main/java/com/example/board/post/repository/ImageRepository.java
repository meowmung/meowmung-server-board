package com.example.board.post.repository;

import com.example.board.common.s3.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
