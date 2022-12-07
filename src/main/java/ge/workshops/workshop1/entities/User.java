package ge.workshops.workshop1.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
@SequenceGenerator(name = "userIdGenerator", sequenceName = "users_id_seq", allocationSize = 1)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGenerator")
    private Integer id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "create_date") //insertable = false, updatable = false
    private LocalDateTime create_date;
    private Boolean active;

  //  @PrePersist
   // private void prePersist() {
    //    active = true;
    //    create_date = LocalDateTime.now();
    //}
}