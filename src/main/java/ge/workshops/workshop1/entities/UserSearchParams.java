package ge.workshops.workshop1.entities;

import lombok.Data;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UserSearchParams {
    private int id;
    private String username;
    private String password;
    private String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate create_date;
    private boolean active;
}
