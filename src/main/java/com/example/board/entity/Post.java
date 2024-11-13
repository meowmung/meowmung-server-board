package com.example.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long postId;
    public String title;
    public String content;
    public String author;
    public Date createdAt;
    public Date updateAt;
    public Integer viewCount;
}
