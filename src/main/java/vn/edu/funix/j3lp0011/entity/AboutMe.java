package vn.edu.funix.j3lp0011.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "about_me")
public class AboutMe {
    @Id
    private int id;
    @Column(length = 4000)
    private String content;
    private String author;
}