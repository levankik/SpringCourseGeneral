package ge.workshops.workshop1.controller;



import ge.workshops.workshop1.dto.CustomerDto;
import ge.workshops.workshop1.services.CustomerService;
import ge.workshops.workshop1.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/customers")

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @GetMapping()
    public List<Customer> getAll(CustomerDto searchParams) {

        return customerService.getAll(searchParams);
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable int id) {

        return customerService.getCustomer(id);
    }

    @PostMapping()
    public ResponseEntity<Customer> add(@RequestBody Customer customer) {
        customerService.add(customer);
        var location = UriComponentsBuilder.fromPath("/customers/" + customer.getId()).build().toUri();
        return ResponseEntity.created(location).body(customer);
    }

    @PutMapping("/{id}")
    public Customer  update(@RequestBody Customer customer, @PathVariable int id) {
        return customerService.update(id, customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable int id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

