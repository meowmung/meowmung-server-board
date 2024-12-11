package com.example.board.complain.entity;

import com.example.board.comment.entity.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "comment_complain")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentComplain {
    @Id
    @Column(name = "comment_complain_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long CommentComplainId;

//    public Member reporter;

    @Column(name = "comment_complain_content", nullable = false)
    public String commentComplainContent;

    @Column(name = "comment_complain_count")
    @Builder.Default
    public int commentComplainCount = 0;

    @Column(name = "created_at")
    @CreationTimestamp
    public Date createdAt;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    public Comment comment;

//    @PrePersist
//    public void prePersist() {
//        if (this.commentComplainCount == null) {
//            this.commentComplainCount = 0;
//        }
//    }

    public void setCommentComplainCount(int commentComplainCount) {
        this.commentComplainCount = commentComplainCount;
    }

}
