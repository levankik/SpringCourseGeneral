package ge.workshops.workshop1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "permissions")
@SequenceGenerator(name = "idGenerator", sequenceName = "permissions_seq", allocationSize = 1)
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    private Integer id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private List<User> users;
}
