package ge.workshops.workshop1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
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

    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @Column(name = "active")
    private Boolean active;

    @JsonIgnore
    @OneToMany (mappedBy = "user", fetch = FetchType.EAGER)
    private List<Post> posts;

    @PrePersist
    public void prePersist() {
        createDate = LocalDateTime.now();
        active = true;
    }

}
