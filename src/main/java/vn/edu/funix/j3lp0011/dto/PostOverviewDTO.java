package vn.edu.funix.j3lp0011.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PostOverviewDTO {
    private int id;
    private String title;
    private LocalDate createdAt;
}