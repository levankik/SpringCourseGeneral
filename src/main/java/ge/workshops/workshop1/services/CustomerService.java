package ge.workshops.workshop1.services;

import ge.workshops.workshop1.dto.CustomerSearchParams;
import ge.workshops.workshop1.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll (CustomerSearchParams searchParams);
    Customer add(Customer customer);
    Customer update(int id, Customer customer);
    void delete(int id);
    Customer  getCustomer(int id);
}

