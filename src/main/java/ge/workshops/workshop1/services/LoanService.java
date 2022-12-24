package ge.workshops.workshop1.services;

import ge.workshops.workshop1.dto.PostSearchParams;
import ge.workshops.workshop1.entity.Loan;
import ge.workshops.workshop1.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoanService {
    Loan addLoan(Loan loan);
}
