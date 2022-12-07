package ge.workshops.workshop1.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserSearchParams {
    private String username;
    private String password;
    private String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime create_date;
    private boolean active;
}

