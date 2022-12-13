package ge.workshops.workshop1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "active")
    private boolean active;

    @JsonBackReference
    @OneToMany (mappedBy = "user", fetch = FetchType.EAGER)
    private List<Post> posts;

}
