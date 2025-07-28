package vn.edu.funix.j3lp0011.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "social")
public class Social {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String icon;
}