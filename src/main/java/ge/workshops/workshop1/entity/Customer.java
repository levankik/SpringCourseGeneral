package ge.workshops.workshop1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ge.workshops.workshop1.dto.LoanRegistrationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "customers")
@SequenceGenerator(name = "customerIdGenerator", sequenceName = "customers_id_seq", allocationSize = 1)
public class Customer {

    public Customer(LoanRegistrationDto.Customer dto, String username) {
        this.privateNumber = dto.getPrivateNumber();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.birthDate = dto.getBirthDate();
        this.createdBy = username;
        this.updatedBy = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerIdGenerator")
    private  Integer id;
    @Column(name = "private_number", nullable = false, updatable = true)
    private  String privateNumber;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @Column(name = "created_by", nullable = false, updatable = false)
    private String  createdBy;
    @Column(name = "updated_by", nullable = false, updatable = false)
    private String updatedBy;

    @JsonIgnore
    @OneToMany (mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Loan> loans;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
}


