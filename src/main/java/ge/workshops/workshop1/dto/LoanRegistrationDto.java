package ge.workshops.workshop1.dto;

import ge.workshops.workshop1.entity.Collateral.CollateralType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRegistrationDto {

    @Valid
    @NotNull
    private Loan loan;
    @Valid
    @NotNull
    private Customer customer;
    @Valid
    @NotNull
    private List<Collateral> collaterals;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Loan {
        @NotBlank
        private String loanNumber;
        @NotNull
        @Min(100)
        private  Double amount;
        @Min(2)
        @Max(100)
        private Integer term;
        @NotNull
        @Min(1)
        private Double interestRate;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Customer {
        @NotBlank
        private String privateNumber;
        private String firstName;
        private String lastName;
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate birthDate;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Collateral {
        @NotNull
        private CollateralType type;
        @NotNull
        private Double value;
    }

}
