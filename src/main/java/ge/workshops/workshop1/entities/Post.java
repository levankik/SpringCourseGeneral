package ge.workshops.workshop1.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "customers")

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGenerator")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "body")
    private String body;
    @Column(name = "user_id ")
    private Integer user_id;
    @Column(name = "create_date")
    private LocalDateTime create_date;
}

