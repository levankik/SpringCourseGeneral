package ge.workshops.workshop1.services;

import ge.workshops.workshop1.dto.CustomerDto;
import ge.workshops.workshop1.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll (CustomerDto searchParams);
    Customer add(Customer customer);
    Customer update(int id, Customer customer);
    void delete(int id);
    Customer  getCustomer(int id);
}

