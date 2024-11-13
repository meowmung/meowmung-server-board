package com.example.board.entity;

import com.example.board.common.BoardCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long boardId;

    @Enumerated(EnumType.STRING)
    public BoardCategory boardCategory;
}
