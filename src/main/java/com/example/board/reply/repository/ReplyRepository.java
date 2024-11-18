package com.example.board.reply.repository;

import com.example.board.reply.entity.Reply;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Optional<Reply> findByReplyId(Long replyId);

}
