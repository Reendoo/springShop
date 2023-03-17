package sk.peterrendek.learn2code.springshop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sk.peterrendek.learn2code.springshop.domain.Customer;
import sk.peterrendek.learn2code.springshop.domain.Merchant;
import sk.peterrendek.learn2code.springshop.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DBInsertTests {
    private final String sqlInsertCustomer=
            "INSERT INTO customer(name,surname,email,address,age,phone_number) values(?,?,?,?,?,?)";
    private final String sqlInsertMerchant = "INSERT INTO merchant(name,email,address) values (?,?,?)";

    private final String sqlInsertProduct = "INSERT INTO `springshop`.`product`\n" +
            "(merchant_id,name,description,price,created_at,available) VALUES(?,?,?,?,?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    void createCustomer() {
        Customer customer = new Customer();
        customer.setName("Fero");
        customer.setSurname("Mrkva");
        customer.setEmail("mrkva@gmail.com");
        customer.setAddress("fdsifhssf");
        customer.setAge(15);
        customer.setPhoneNumber("06241546541");

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement prs = con.prepareStatement(sqlInsertCustomer);
                prs.setString(1,customer.getName());
                prs.setString(2,customer.getSurname());
                prs.setString(3,customer.getEmail());
                prs.setString(4,customer.getAddress());
                prs.setInt(5,customer.getAge());
                prs.setString(6,customer.getPhoneNumber());
                return prs;
            }
        });
    }

    @Test
    void createMerchant() {
        Merchant merchant = new Merchant( "Feeeeeeriiii","Feeeeeeriiii@gmail.com","fdsuifsdgisuf");
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement prs = con.prepareStatement(sqlInsertMerchant);
                prs.setString(1,merchant.getName());
                prs.setString(2,merchant.getEmail());
                prs.setString(3,merchant.getAddress());
                return  prs;
            }
        });
    }

    @Test
    void product() {
       Product product =new Product(1,"az do marijeeeee","zamocnicke kladivo",130.07,14);
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement prs = con.prepareStatement(sqlInsertProduct);
                prs.setInt(1,7);//product.getMerchand_id()
                prs.setString(2,product.getName());
                prs.setString(3,product.getDescription());
                prs.setDouble(4,product.getPrice());
                prs.setTimestamp(5,product.getCreatedAt());
                prs.setInt(6,product.getAvailable());
                return prs;
            }
        });
    }
}
