package com.example.board.complain.entity;

import com.example.board.comment.entity.Comment;
import com.example.board.post.entity.Post;
import com.example.board.reply.entity.Reply;
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
@Table(name = "reply_complain")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyComplain {
    @Id
    @Column(name = "reply_complain_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long replyComplainId;

//    public Member reporter;

    @Column(name = "reply_complain_content", nullable = false)
    public String replyComplainContent;

    @Column(name = "created_at")
    @CreationTimestamp
    public Date createdAt;

    @Column(name = "reply_complain_count")
    public Integer replyComplainCount = 0;

//    @ManyToOne
//    @JoinColumn(name = "post_id", nullable = false)
//    public Post post;

    @ManyToOne
    @JoinColumn(name = "reply_id", nullable = false)
    public Reply reply;

    @PrePersist
    public void prePersist() {
        if (this.replyComplainCount == null) {
            this.replyComplainCount = 0;
        }
    }

    public void setreplyComplainCount(Integer replyComplainCount) {
        this.replyComplainCount = replyComplainCount;
    }

}
