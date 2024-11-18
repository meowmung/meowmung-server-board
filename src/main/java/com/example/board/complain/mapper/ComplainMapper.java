package com.example.board.complain.mapper;

import com.example.board.complain.dto.response.ComplainResponse;
import com.example.board.complain.entity.Complain;
import org.springframework.stereotype.Component;

@Component
public class ComplainMapper {
    public ComplainResponse toResponse(Complain complain) {
        return new ComplainResponse(
                complain.getComplainId(),
                complain.getComplainContent(),
                complain.getCreatedAt(),
                complain.getPost().getPostId(),
                complain.getPost().getContent()
        );
    }
}
