package sk.peterrendek.learn2code.springshop.db.services.impl;

import org.springframework.stereotype.Service;
import sk.peterrendek.learn2code.springshop.db.repositories.CustomerRepository;
import sk.peterrendek.learn2code.springshop.db.services.api.CustomerService;
import sk.peterrendek.learn2code.springshop.domain.Customer;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repository.getAll();
    }

    @Override
    public Customer get(int id) {
       return repository.get(id);
    }

    @Override
    public Integer add(Customer customer) {
       return repository.add(customer);
    }
}
