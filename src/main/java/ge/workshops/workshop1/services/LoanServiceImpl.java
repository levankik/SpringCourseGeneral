package ge.workshops.workshop1.services;

import ge.workshops.workshop1.dto.LoanRegistrationDto;
import ge.workshops.workshop1.entity.Collateral;
import ge.workshops.workshop1.entity.Customer;
import ge.workshops.workshop1.entity.Loan;
import ge.workshops.workshop1.exceptions.NotFoundException;
import ge.workshops.workshop1.repository.CollateralRepository;
import ge.workshops.workshop1.repository.CustomerRepository;
import ge.workshops.workshop1.repository.LoanRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final CollateralRepository collateralRepository;
    private final CustomerRepository customerRepository;


    public LoanServiceImpl(LoanRepository loanRepository,
                           CollateralRepository collateralRepository,
                           CustomerRepository customerRepository) {
        this.loanRepository = loanRepository;
        this.collateralRepository = collateralRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Loan register(LoanRegistrationDto loanRegistrationDto) {
        var customerDto = loanRegistrationDto.getCustomer();
        var customer = new Customer(customerDto);
        customerRepository.save(customer);

        var loanDto = loanRegistrationDto.getLoan();
        var loan = new Loan(loanDto);
        loan.setCustomer(customer);
        loanRepository.save(loan);

        var collateralDtos = loanRegistrationDto.getCollaterals();
        collateralDtos.forEach(collateralDto -> {
            var collateral = new Collateral(collateralDto);
            collateral.setLoan(loan);
            collateralRepository.save(collateral);
        });

        return loan;

    }

    @Override
    public Loan get (int id) {
        return loanRepository.findById(id).orElseThrow(() -> new NotFoundException("Loan not found"));
    }

    @Scheduled (fixedRate = 60 * 1000)
    public void calculateInterest() {
        loanRepository.findAll().forEach(this::updateInterest);
    }

    public void updateInterest(Loan loan) {
        var interestRate = loan.getInterestRate();
        var dailyInterestRate = interestRate / 365;
        var timeDiff = Math.abs(Duration.between(loan.getCreatedAt(), LocalDateTime.now()).toMinutes());
        var interest = loan.getAmount() * dailyInterestRate / (24 * 60) * timeDiff;
        loan.setInterest((float) interest); // ვანოსთან დაქასთვა არ დასჭირვებია
        loanRepository.save(loan);
    }
}
