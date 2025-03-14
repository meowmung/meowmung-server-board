package com.example.board.post.entity;

import com.example.board.board.entity.Board;
import com.example.board.comment.entity.Comment;
import com.example.board.common.s3.entity.Image;
import com.example.board.complain.entity.PostComplain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "post")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long postId;

    @Column(name = "title", nullable = false)
    public String title;

    @Column(name = "content", nullable = false)
    public String content;

    @Column(name = "member_nickname")
    public String nickname;

    @Column(name = "created_at")
    @CreationTimestamp
    public Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    public Date updatedAt;

    @Column(name = "view_count")
    public Integer viewCount;
    // 신고 횟수 관리
    @Column(name = "post_complain_count")
    public Integer postComplainCount;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "board_category", nullable = false)
    @JsonIgnore
    public Board board;

    // 하나의 게시글은 여러 개의 신고를 가질 수 있다.
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    public List<PostComplain> postComplains = new ArrayList<>();

    // 하나의 게시글은 여러 개의 댓글을 가질 수 있다
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    public List<Comment> comments = new ArrayList<>();

    // 하나의 게시글은 여러 개의 이미지
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    public List<Image> images = new ArrayList<>();

//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    public List<Reply> reply = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.postComplainCount == null) {
            this.postComplainCount = 0;
        }
        if (this.viewCount == null) {
            this.viewCount = 0;
        }
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addImages(List<Image> images) {
        this.images = images;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public void setPostComplainCount(Integer postComplainCount) {
        this.postComplainCount = postComplainCount;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", nickname='" + nickname + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", viewCount=" + viewCount +
                ", postComplainCount=" + postComplainCount +
                ", memberId=" + memberId +
                ", board=" + board +
                ", postComplains=" + postComplains +
                ", comments=" + comments +
                '}';
    }
}