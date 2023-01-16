
package ge.workshops.workshop1.services;

        import ge.workshops.workshop1.dto.LoanRegistrationDto;
        import ge.workshops.workshop1.entity.Loan;
        import org.springframework.stereotype.Service;

@Service
public interface LoanService {
    Loan register(LoanRegistrationDto loanDto);
    Loan get(int id);
    void updateInterest(Loan loan);
}