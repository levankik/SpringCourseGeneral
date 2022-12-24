package ge.workshops.workshop1.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class LoanDto {
    private String loan_number;
    private  float amount;
    private float interest_rate;
    private int term;
    private float interest;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
}
