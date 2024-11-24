    package com.example.board.board.repository;

    import com.example.board.board.entity.Board;
    import com.example.board.post.entity.Post;
    import java.util.Optional;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.repository.JpaRepository;

    public interface BoardRepository extends JpaRepository<Board, Long> {
        Page<Post> findPageBy(Pageable pageable);
        Optional<Board> findAllByBoardCategory(String boardCategory);
        Optional<Board> findByBoardCategory(String boardCategory);
    }
