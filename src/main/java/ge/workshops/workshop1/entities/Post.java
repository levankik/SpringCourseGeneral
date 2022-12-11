package ge.workshops.workshop1.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "posts")
@SequenceGenerator(name = "postIdGenerator", sequenceName = "posts_id_seq", allocationSize = 1)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postIdGenerator")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "body")
    private String body;
    @Column(name = "user_id")
    private Integer user_id;
    @Column(name = "create_date")
    private LocalDate create_date;
}

