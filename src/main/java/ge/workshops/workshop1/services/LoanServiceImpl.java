package ge.workshops.workshop1.services;

import ge.workshops.workshop1.dto.RegistrationDto;
import ge.workshops.workshop1.entity.Loan;
import ge.workshops.workshop1.repository.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {

        this.loanRepository = loanRepository;
    }

    @Override
    public Loan addLoan(RegistrationDto registrationDto) {
        Loan loan = new Loan();
        loan.setLoan_number(registrationDto.getLoan().getLoan_number());
        return loan;
    }


}
