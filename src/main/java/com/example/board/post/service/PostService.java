package com.example.board.post.service;

import com.example.board.board.entity.Board;
import com.example.board.board.repository.BoardRepository;
import com.example.board.common.s3.entity.Image;
import com.example.board.post.dto.request.PostEditRequest;
import com.example.board.post.dto.request.PostOneResponse;
import com.example.board.post.dto.request.PostRequest;
import com.example.board.post.entity.Post;
import com.example.board.post.repository.ImageRepository;
import com.example.board.post.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;

    // 게시글 저장
    public PostOneResponse savePost(String boardCategory, PostRequest postRequest, String nickname, Long memberId) {
        Board board = boardRepository.findById(boardCategory)
                .orElseThrow(() -> new RuntimeException("해당 카테고리가 존재하지 않습니다."));

        Post post = postRequest.toEntity(board, nickname, memberId);
        Post save = postRepository.save(post);

        // 이미지 url 로 image 객체 만들어서
        List<Image> images = new ArrayList<>();
        if (postRequest.imageUrls() != null && !postRequest.imageUrls().isEmpty()) {
            for (String imageUrl : postRequest.imageUrls()) {
                Image image = Image.builder()
                        .s3Key(generateS3KeyFormUrl(imageUrl))
                        .post(post)
                        .build();
                imageRepository.save(image);
                images.add(image);
            }
        }

        save.addImages(images);

        return PostOneResponse.fromEntity(save);
    }

    // S3 Key 생성
    public String generateS3KeyFormUrl(String imageUrl) {
        String[] parts = imageUrl.split("/");
        String s3Key = "board_image/" + parts[parts.length - 1];
        return s3Key;
    }

    // postId 로 게시글 찾기
    public PostOneResponse getPost(Long postId) {
//        Optional<Post> opt = postRepository.findByPostId(postId);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 게시물 입니다."));
        this.incrementViewCount(post);
        return PostOneResponse.fromEntity(post);
    }

    // 게시글 삭제
    @Transactional
    public ResponseEntity deletePost(Long postId, Long memberId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException("해당 게시물은 존재하지 않습니다."));

        if (!post.getMemberId().equals(memberId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("삭제 권한이 없습니다.");
        }
        postRepository.delete(post);
        return ResponseEntity.status(HttpStatus.OK).body("게시글이 삭제되었습니다.");
    }

    // 게시글 수정
    public Post updatePost(Long postId, PostEditRequest postEditRequest) {
        Post post = postRepository.findByPostId(postId).orElseThrow(() -> new RuntimeException("해당 게시물은 존재하지 않습니다."));
        post.update(postEditRequest.title(), postEditRequest.content());
        return postRepository.save(post);
    }

    // 조회수
    public void incrementViewCount(Post post) {
        post.setViewCount(post.getViewCount() + 1);
        postRepository.save(post);
    }

    // 신고 - 누적 신고 횟수 확인 하고 5가 되면 게시글 삭제
    @Transactional
    public void checkAndDeletePost(Long postId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException("해당 게시물은 존재하지 않습니다."));

        if (post.getPostComplainCount() >= 5) {
            postRepository.delete(post);
        }
    }
}
