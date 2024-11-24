    package com.example.board.board.entity;

    import com.example.board.post.entity.Post;
    import jakarta.persistence.CascadeType;
    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.FetchType;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.OneToMany;
    import jakarta.persistence.PrePersist;
    import jakarta.persistence.Table;
    import java.util.ArrayList;
    import java.util.List;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Getter;
    import lombok.NoArgsConstructor;

    @Entity
    @Table(name = "board")
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class Board {
        @Id
        @Column(name = "board_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public Long boardId;

        @Column(name = "board_category", nullable = false)
        public String boardCategory;

        @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        public List<Post> posts = new ArrayList<>();


    }
