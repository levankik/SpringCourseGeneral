package ge.workshops.workshop1.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegistrationDto {
    private CustomerDto customer;
    private LoanDto loan;
    private List<CollateralDto> collaterals;
}
