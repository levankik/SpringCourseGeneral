package ge.workshops.workshop1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
@SequenceGenerator(name = "customerIdGenerator", sequenceName = "customers_id_seq", allocationSize = 1)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerIdGenerator")
    private  Integer id;
    @Column(name = "private_number")
    private  String private_number;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @JsonIgnore
    @OneToMany (mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Loan> loans;
}


