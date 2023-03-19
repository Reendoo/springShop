package sk.peterrendek.learn2code.springshop.db.services;

import org.springframework.lang.Nullable;
import sk.peterrendek.learn2code.springshop.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    @Nullable
    Customer get(int id);

    @Nullable
    Integer add(Customer customer); //generovane id;


}
