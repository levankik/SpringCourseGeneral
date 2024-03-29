package ge.workshops.workshop1.services;

import ge.workshops.workshop1.dto.LoanRegistrationDto;
import ge.workshops.workshop1.entity.Collateral;
import ge.workshops.workshop1.entity.Customer;
import ge.workshops.workshop1.entity.Loan;
import ge.workshops.workshop1.repository.CollateralRepository;
import ge.workshops.workshop1.repository.CustomerRepository;
import ge.workshops.workshop1.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@SpringBootTest
class LoanServiceImplTest {

    @Autowired
    private LoanServiceImpl loanServiceImpl;

    @MockBean
    public LoanRepository loanRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CollateralRepository collateralRepository;

    @Test
    public void testLoanRegister() {
        var customerDto = new LoanRegistrationDto.Customer( // ra custer ?
                "01010101010", "John", "Doe", LocalDate.of(1990, 1, 1));
        var loanDto = new LoanRegistrationDto.Loan("123456789", 1000.0, 12, 12.0);
        var collateralDto = new LoanRegistrationDto.Collateral(Collateral.CollateralType.HOUSE, 1000.0);
        var loanRegistrationDto = new LoanRegistrationDto(loanDto, customerDto, List.of(collateralDto));

        //Loan loan2 = new Loan(loanDto, username);
        //when(loanRepository.save(loan2)).thenReturn(loan2);

        var loan = loanServiceImpl.register(loanRegistrationDto, "vano");
        assertNotNull(loan);
        assertEquals(loanDto.getAmount(), loan.getAmount());
        assertNotNull(loan.getCustomer());
        assertEquals(loan.getCreatedBy(), "vano");
        assertEquals(loan.getUpdatedBy(), "vano");

        verify(loanRepository, times(1)).save(loan);
        verify(customerRepository, times(1)).save(any(Customer.class));
        verify(collateralRepository, times(1)).save(any());

    }


    @Test
    public void testLoanRegister_when_private_number_is_null() {

        var custerDto = new LoanRegistrationDto.Customer(
                null, "John", "Doe", LocalDate.of(1990, 1, 1));
        var loanDto = new LoanRegistrationDto.Loan("123456789", 1000.0, 12, 12.0);
        var collateralDto = new LoanRegistrationDto.Collateral(Collateral.CollateralType.HOUSE, 1000.0);
        var loanRegistrationDto = new LoanRegistrationDto(loanDto, custerDto, List.of(collateralDto));

        //assertThrows(IllegalArgumentException.class, () -> loanServiceImpl.register(loanRegistrationDto, "vano"));

    }

    @Test
    public void testLoanRegister_when_loan_number_is_null() {
        var customerDto = new LoanRegistrationDto.Customer(
                "01010101", "John", "Doe", LocalDate.of(1990, 1, 1));
        var loanDto = new LoanRegistrationDto.Loan(null, 1000.0, 12, 12.0);
        var collateralDto = new LoanRegistrationDto.Collateral(Collateral.CollateralType.HOUSE, 1000.0);
        var loanRegistrationDto = new LoanRegistrationDto(loanDto, customerDto, List.of(collateralDto, collateralDto));
        loanServiceImpl.register(loanRegistrationDto, "vano");
        //assertThrows(IllegalArgumentException.class, () -> loanServiceImpl.register(loanRegistrationDto));
    }

    @Test
    void updateInterest() {
        var loan = new Loan();
        loan.setAmount(1000.0);
        loan.setInterestRate(12.0);
        loan.setCreatedAt(LocalDateTime.of(2020, 1, 1, 1, 1, 1));
        loanServiceImpl.updateInterest(loan, LocalDateTime.of(2020, 1, 1, 2, 1, 1));
        assertEquals(1.3698630332946777, loan.getInterest());
        assertEquals(1000.0, loan.getAmount());
        assertEquals(12.0, loan.getInterestRate());
    }
}