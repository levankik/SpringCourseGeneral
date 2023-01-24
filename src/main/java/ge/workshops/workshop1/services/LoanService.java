
package ge.workshops.workshop1.services;

        import ge.workshops.workshop1.config.SecUser;
        import ge.workshops.workshop1.dto.LoanRegistrationDto;
        import ge.workshops.workshop1.entity.Loan;
        import org.springframework.stereotype.Service;

        import java.time.LocalDateTime;

@Service
public interface LoanService {
    Loan register(LoanRegistrationDto loanDto, String username);
    Loan get(int id);
    void updateInterest(Loan loan, LocalDateTime endTime);
}