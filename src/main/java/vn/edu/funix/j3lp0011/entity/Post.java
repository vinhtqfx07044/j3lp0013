package vn.edu.funix.j3lp0011.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String type;
    @Column(length = 2000)
    private String content;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "created_at")
    private LocalDate createdAt;
}
