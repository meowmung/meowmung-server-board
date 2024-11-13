package com.example.board.entity;

import com.example.board.common.BoardCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "board")
public class Board {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long boardId;

    @Column(name = "board_category", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    public BoardCategory boardCategory;
}
