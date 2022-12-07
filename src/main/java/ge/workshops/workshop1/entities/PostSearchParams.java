package ge.workshops.workshop1.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PostSearchParams {
    private int id;
    private String title;
    private String body;
    private String user_id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime create_date;
}