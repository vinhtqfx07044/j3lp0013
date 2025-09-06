package vn.edu.funix.j3lp0011.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.funix.j3lp0011.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByOrderByCreatedAtDesc(Pageable pageable);

    @Query(value = """
            SELECT
                UPPER(TO_CHAR(created_at, 'Month YYYY')) as group_key,
                id, title, created_at
            FROM post
            ORDER BY created_at DESC
            """, nativeQuery = true)
    List<Object[]> findPostsGroupedByMonthYear();
}