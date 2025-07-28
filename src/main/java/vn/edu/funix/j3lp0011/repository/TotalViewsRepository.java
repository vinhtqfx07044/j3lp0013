package vn.edu.funix.j3lp0011.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vn.edu.funix.j3lp0011.entity.TotalViews;

public interface TotalViewsRepository extends JpaRepository<TotalViews, Integer> {
    @Modifying
    @Query("UPDATE TotalViews v SET v.viewCount = v.viewCount + 1 WHERE v.id = 1")
    void incrementViewCount();
}