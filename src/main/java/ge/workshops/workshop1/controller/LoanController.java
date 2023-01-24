package ge.workshops.workshop1.controller;

import ge.workshops.workshop1.config.SecUser;
import ge.workshops.workshop1.dto.LoanRegistrationDto;
import ge.workshops.workshop1.entity.Loan;
import ge.workshops.workshop1.services.LoanService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RequestMapping("/loans")
@RestController
@PreAuthorize("hasAuthority('LOAN_READ')")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PreAuthorize("hasAuthority('LOAN_ADD')")
    @PostMapping("/register")
    public ResponseEntity<Loan> register(@RequestBody @Valid LoanRegistrationDto loanDto,
                                         @Parameter(hidden = true) @AuthenticationPrincipal SecUser user) {
        Loan registered = loanService.register(loanDto, user.getUsername());
        var location = UriComponentsBuilder.fromPath("/loans/{id}").buildAndExpand(registered.getId()).toUri();
        return ResponseEntity.created(location).body(registered);
    }

    @GetMapping("/{id}")
    public Loan get(@PathVariable int id) {
        return loanService.get(id);
    }

}
