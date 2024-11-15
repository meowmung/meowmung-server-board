package com.example.board.dto.response;

import com.example.board.entity.Post;
import java.io.Serializable;

public record PostResponse (Post post) implements Serializable {

}
