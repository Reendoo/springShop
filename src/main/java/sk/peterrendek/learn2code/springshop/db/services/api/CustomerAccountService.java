package sk.peterrendek.learn2code.springshop.db.services.api;

import org.springframework.lang.Nullable;
import sk.peterrendek.learn2code.springshop.domain.CustomerAccount;

public interface CustomerAccountService {
    void addCustomerAccount(CustomerAccount customerAccount);

    @Nullable
    Double getMoney(int customer_id);

    void setMoney(int customer_id, double money);


}
