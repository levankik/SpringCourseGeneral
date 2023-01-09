package ge.workshops.workshop1.services;

import ge.workshops.workshop1.dto.RegistrationDto;
import ge.workshops.workshop1.entity.Loan;

public interface LoanService {
    Loan addLoan(RegistrationDto registrationDto);
}
