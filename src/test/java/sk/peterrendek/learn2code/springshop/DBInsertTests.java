package sk.peterrendek.learn2code.springshop;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sk.peterrendek.learn2code.springshop.domain.Customer;
import sk.peterrendek.learn2code.springshop.domain.Merchant;
import sk.peterrendek.learn2code.springshop.domain.Product;

import java.sql.PreparedStatement;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DBInsertTests {
    private final String sqlInsertCustomer;
    private final String sqlInsertMerchant;
    private final String sqlInsertProduct;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBInsertTests(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        sqlInsertProduct = "INSERT INTO product(merchant_id,name,description,price,created_at,available) VALUES(?,?,?,?,?,?)";
        sqlInsertMerchant = "INSERT INTO merchant(name,email,address) values (?,?,?)";
        sqlInsertCustomer = "INSERT INTO customer(name,surname,email,address,age,phone_number) values(?,?,?,?,?,?)";
    }


    @Test
    void createCustomer() {
        Customer customer = new Customer();
        customer.setName("Fero");
        customer.setSurname("Mrkva");
        customer.setEmail("mrkva@gmail.com");
        customer.setAddress("fdsifhssf");
        customer.setAge(15);
        customer.setPhoneNumber("06241546541");

        jdbcTemplate.update(con -> {
            PreparedStatement prs = con.prepareStatement(sqlInsertCustomer);
            prs.setString(1, customer.getName());
            prs.setString(2, customer.getSurname());
            prs.setString(3, customer.getEmail());
            prs.setString(4, customer.getAddress());
            prs.setInt(5, customer.getAge() == null ? 0 : customer.getAge());
            prs.setString(6, customer.getPhoneNumber());
            return prs;
        });
    }


    @Test
    void createMerchant() {
        Merchant merchant = new Merchant("Feeeeeeriiii", "Feeeeeeriiii@gmail.com", "fdsuifsdgisuf");
        jdbcTemplate.update(con -> {
            PreparedStatement prs = con.prepareStatement(sqlInsertMerchant);
            prs.setString(1, merchant.getName());
            prs.setString(2, merchant.getEmail());
            prs.setString(3, merchant.getAddress());
            return prs;
        });

    }

    @Test
    void product() {
        Product product = new Product(1, "az do marijeeeee", "zamocnicke kladivo", 130.07, 14);
        jdbcTemplate.update(con -> {
            PreparedStatement prs = con.prepareStatement(sqlInsertProduct);
            prs.setInt(1, 1);//product.getMerchand_id()
            prs.setString(2, product.getName());
            prs.setString(3, product.getDescription());
            prs.setDouble(4, product.getPrice());
            prs.setTimestamp(5, product.getCreatedAt());
            prs.setInt(6, product.getAvailable());
            return prs;
        });
    }
}
