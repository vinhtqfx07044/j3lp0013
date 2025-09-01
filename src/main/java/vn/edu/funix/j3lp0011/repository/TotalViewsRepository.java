package vn.edu.funix.j3lp0011.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vn.edu.funix.j3lp0011.entity.TotalViews;

public interface TotalViewsRepository extends JpaRepository<TotalViews, Integer> {
    @Modifying
    @Query(value = "UPDATE total_views SET view_count = view_count + 1 WHERE id = 1 RETURNING view_count", nativeQuery = true)
    int incrementAndGetViewCount();
}