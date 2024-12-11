//package com.example.board.board.initializer;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.example.board.board.entity.Board;
//import com.example.board.board.repository.BoardRepository;
//import java.util.Optional;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class BoardInitializerTest {
//
//    @Autowired
//    private BoardRepository boardRepository;
//
//    @Test
//    @DisplayName("board 초기화 테스트")
//    void test(){
//        Optional<Board> free = boardRepository.findById("free");
//        assertTrue(free.isPresent());
//    }
//}