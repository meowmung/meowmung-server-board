package com.example.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long postId;
    @Column(name = "title", nullable = false)
    public String title;
    @Column(name = "content", nullable = false)
    public String content;
    @Column(name = "author", nullable = false)
    public String author;
    @Column(name = "created_at")
    public Date createdAt;
    @Column(name = "updated_at")
    public Date updatedAt;
    @Column(name = "view_count")
    public Integer viewCount;
}