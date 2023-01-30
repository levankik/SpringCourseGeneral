package ge.workshops.workshop1.entity;

import ge.workshops.workshop1.dto.PostJP;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    public Post(PostJP postJP) {
        body = postJP.getBody();
        title = postJP.getTitle();
    }

    public Post(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "create_date",  updatable = false)
    private  LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @PrePersist
    public void prePersist() {
        createDate = LocalDateTime.now();
    }
}
