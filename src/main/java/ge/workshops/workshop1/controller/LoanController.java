package ge.workshops.workshop1.controller;

import ge.workshops.workshop1.dto.LoanRegistrationDto;
import ge.workshops.workshop1.entity.Loan;
import ge.workshops.workshop1.services.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/loans")
@RestController
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/register")
    public ResponseEntity<Loan> register(@RequestBody LoanRegistrationDto loanDto) {
        Loan registered = loanService.register(loanDto);
        var location = UriComponentsBuilder.fromPath("/loans/{id}").buildAndExpand(registered.getId()).toUri();
        return ResponseEntity.created(location).body(registered);
    }

    @GetMapping("/{id}")
    public Loan get(@PathVariable int id) {
        return loanService.get(id);
    }

}
