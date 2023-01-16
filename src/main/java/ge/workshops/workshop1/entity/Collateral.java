package ge.workshops.workshop1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ge.workshops.workshop1.dto.LoanRegistrationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "collateral")
@SequenceGenerator(name = "collateralIdGenerator", sequenceName = "collateral_id_seq", allocationSize = 1)
public class Collateral {

    public Collateral(LoanRegistrationDto.Collateral dto) {
        this.type = dto.getType();
        this.value = dto.getValue();
    }

    public enum CollateralType {
        VEHICLE,
        HOUSE,
        REAL_ESTATE
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collateralIdGenerator")
    private int id;
    @Column(name = "type", nullable = false)
    private  CollateralType type;
    @Column(name = "value")
    private Double value;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private Loan loan;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
}
