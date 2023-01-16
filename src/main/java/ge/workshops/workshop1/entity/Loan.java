package ge.workshops.workshop1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ge.workshops.workshop1.dto.LoanRegistrationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "loan")
@SequenceGenerator(name = "loanIdGenerator", sequenceName = "loan_id_seq", allocationSize = 1)
public class Loan {

    public Loan(LoanRegistrationDto.Loan dto) {
        if(dto == null) {
            throw new IllegalArgumentException("loan is null");
        }
        this.amount = dto.getAmount();
        this.interestRate = dto.getInterest_rate();
        this.loanNumber = dto.getLoan_number();
        this.term = dto.getTerm();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loanIdGenerator")
    private Integer id;
    @Column(name = "amount", nullable = false)
    private Double amount;
    @Column(name = "interest_rate", nullable = false)
    private Double interestRate;
    @Column(name = "interest", nullable = false)
    private float interest;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @Column(name = "loan_number", nullable = false)
    private String loanNumber;
    @Column(name = "term")
    private int term;



    @PrePersist
    public void prePersist() {
        loanNumber = "";
        interestRate = 0.0;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    @OneToMany (mappedBy = "loan")
    private List<Collateral> collaterals;

}
