package ge.workshops.workshop1.entity;

import lombok.Data;

import javax.persistence.*;
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
    private int id;
    @Column(name = "type")
    private  CollateralType type;
    @Column(name = "price")
    private float price;
    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private Loan loan;
}
