package com.example.board.complain.entity;

import com.example.board.post.entity.Post;
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
@Table(name = "post_complain")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostComplain {
    @Id
    @Column(name = "post_complain_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long postComplainId;

//    public Member reporter;

    @Column(name = "post_complain_content", nullable = false)
    public String postComplainContent;

    @Column(name = "post_complain_count")
    public int postComplainCount = 0;

    @Column(name = "created_at")
    @CreationTimestamp
    public Date createdAt;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    public Post post;

//    @PrePersist
//    public void prePersist() {
//        if (this.postComplainCount == null) {
//            this.postComplainCount = 0;
//        }
//    }

    public void setPostComplainCount(int postComplainCount) {
        this.postComplainCount = postComplainCount;
    }

}
