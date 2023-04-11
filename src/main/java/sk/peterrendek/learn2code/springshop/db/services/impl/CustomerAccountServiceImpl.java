package sk.peterrendek.learn2code.springshop.db.services.impl;

import org.springframework.stereotype.Service;
import sk.peterrendek.learn2code.springshop.db.repositories.CustomerAccountRepository;
import sk.peterrendek.learn2code.springshop.db.services.api.CustomerAccountService;
import sk.peterrendek.learn2code.springshop.domain.CustomerAccount;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {
    private CustomerAccountRepository repository;

    public CustomerAccountServiceImpl(CustomerAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addCustomerAccount(CustomerAccount customerAccount) {
        repository.add(customerAccount);
    }

    @Override
    public Double getMoney(int customer_id) {
        return repository.getMoney(customer_id);
    }

    @Override
    public void setMoney(int customer_id, double money) {
        repository.setMoney(customer_id,money);
    }
}
