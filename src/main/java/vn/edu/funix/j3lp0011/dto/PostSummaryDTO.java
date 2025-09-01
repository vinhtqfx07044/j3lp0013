package vn.edu.funix.j3lp0011.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PostSummaryDTO {
    private int id;
    private String title;
    private String type;
    private String content;
    private String imagePath;
    private LocalDate createdAt;
    private String quoteText;
    private String quoteAuthor;
}