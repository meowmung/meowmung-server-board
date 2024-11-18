package com.example.board.complain.repository;

import com.example.board.complain.entity.Complain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplainRepository extends JpaRepository<Complain, Long> {

}
