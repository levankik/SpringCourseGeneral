package ge.workshops.workshop1.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "collateral")
public class Collateral {
    public enum CollateralType {
        CAR,
        APARTMENT,
        LAND
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collateralIdGenerator")
    private  Integer id;
    @Column(name = "type")
    private  CollateralType type;
    @Column(name = "value")
    private  float value;
    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private Loan loan;

}
