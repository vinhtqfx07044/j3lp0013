package vn.edu.funix.j3lp0011.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "total_views")
public class TotalViews {
    @Id
    private int id;
    @Column(name = "view_count")
    private int viewCount;
}