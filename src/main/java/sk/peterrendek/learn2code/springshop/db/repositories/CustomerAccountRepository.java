package sk.peterrendek.learn2code.springshop.db.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sk.peterrendek.learn2code.springshop.domain.CustomerAccount;

@Component
public class CustomerAccountRepository {
    private final JdbcTemplate jdbcTemplate;

    public CustomerAccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void add(CustomerAccount customerAccount){
        final String sql = "insert into customer_account(customer_id, money) values(?,?)";
        jdbcTemplate.update(sql,customerAccount.getCustomerId(),customerAccount.getMoney());
    }
    public Double getMoney(int customer_id){
        final String sql = "select money from customer_account where customer_id=?";
        try{
            return jdbcTemplate.queryForObject(sql, Double.class,customer_id);
        }catch (DataAccessException e){
            return null;
        }
    }
    public void setMoney(int customer_id, double money){
        final String sql = "update customer_account set money = ? where customer_id =?";
        jdbcTemplate.update(sql,money,customer_id);
    }
}

