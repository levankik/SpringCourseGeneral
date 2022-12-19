package ge.workshops.workshop1.services;


import ge.workshops.workshop1.dto.CustomerSearchParams;
import ge.workshops.workshop1.exceptions.InvalidParameterException;
import ge.workshops.workshop1.exceptions.NotFoundException;
import ge.workshops.workshop1.repository.CustomerRepository;
import ge.workshops.workshop1.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<Customer> getAll (CustomerSearchParams searchParams) {
        return customerRepository.findActive();
        //String firstName = searchParams.getFirstName();
        //String lastName = searchParams.getLastName();
        //LocalDate birthDate= searchParams.getBirthDate();
        //var stream =  db.stream().filter(c -> !c.getDeleted());
        //if (firstName != null && !firstName.isEmpty()) {
        //    stream = stream.filter(c -> c.getFirstName().equals(firstName));
        //}
        //if (lastName != null && !lastName.isEmpty()) {
        //    stream = stream.filter(c -> c.getLastName().equals(lastName));
        //}
        //if (birthDate != null) {
        //    stream = stream.filter(c -> c.getBirthDate().isEqual(birthDate));
        //}
        //return stream.toList();
    }


    public  Customer add(Customer customer) {
        customer.setId(null);
        customer.setDeleted(false);
        return customerRepository.save(customer);
    }


    public Customer update(int id, Customer customer) {
        var foundCustomer = getCustomer(id);
        foundCustomer.setFirstName(customer.getFirstName());
        foundCustomer.setLastName(customer.getLastName());
        foundCustomer.setBirthDate(customer.getBirthDate());
        return customerRepository.save(foundCustomer);
    }

    public void delete(int id) {
        var foundCustomer = getCustomer(id);
        foundCustomer.setDeleted(true);
        customerRepository.save(foundCustomer);
    }

    public Customer getCustomer(int id) {
        if (id < 1) {
            throw new InvalidParameterException("Is must be positive integer");
        }
        return  customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

    }

}

