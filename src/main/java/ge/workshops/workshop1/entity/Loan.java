package ge.workshops.workshop1.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "loans")
@SequenceGenerator(name = "loanIdGenerator", sequenceName = "loans_id_seq", allocationSize = 1)
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loanIdGenerator")
    private  Integer id;

    @Column(name = "loan_number")
    private String loan_number;
    @Column(name = "amount")
    private float amount;
    @Column(name = "interest_rate")
    private float interest_rate;
    @Column(name = "term")
    private int term;
    @Column(name = "interest")
    private float interest;
    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany (mappedBy = "loan")
    private List<Collateral> collaterals;

}
