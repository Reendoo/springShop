package sk.peterrendek.learn2code.springshop;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import sk.peterrendek.learn2code.springshop.db.services.api.CustomerService;
import sk.peterrendek.learn2code.springshop.db.services.api.MerchantService;
import sk.peterrendek.learn2code.springshop.db.services.api.ProductService;
import sk.peterrendek.learn2code.springshop.db.services.api.request.UpdateProductRequest;
import sk.peterrendek.learn2code.springshop.domain.Customer;
import sk.peterrendek.learn2code.springshop.domain.Merchant;
import sk.peterrendek.learn2code.springshop.domain.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DBServiceTest {

    private final CustomerService customerService;
    private final MerchantService merchantService;
    private final ProductService productService;

    @Autowired
    public DBServiceTest(CustomerService customerService, MerchantService merchantService, ProductService productService) {
        this.customerService = customerService;
        this.merchantService = merchantService;
        this.productService = productService;
    }

    private Merchant merchant;

    @BeforeEach
    public void createMerchant() {
        if (merchant == null) {
            merchant = new Merchant("Prvy", "Prvy@gmail.com", "prva");
            Integer id = merchantService.add(merchant);
            assertNotNull(id, "shouldn't be null");
            merchant.setId(id);
        }
    }

    @Test
    void customer() {
        Customer customer = new Customer("Fero", "Mrkva", "mrkva@gmail.com", "Adresa1", 22, "06241546541");
        Integer id = customerService.add(customer);
        assertNotNull(id, "shouldn't be null");
        customer.setId(id);
        Customer customerFromDB = customerService.get(id);
        assertEquals(customer, customerFromDB, "should be same");
        List<Customer> customers = customerService.getAllCustomers();
        assertFalse(customers.isEmpty(), "shouldn't be empty");
        assertEquals(1, customers.size(), "should be 1");
        assertEquals(customer, customers.get(0), "should be same");
    }

    @Test
    void merchant() {
        // merchant is already create
        assertNotNull(merchant.getId());
        Merchant merchantFromDB = merchantService.get(merchant.getId());
        assertEquals(merchant, merchantFromDB, "should be same");
        List<Merchant> merchants = merchantService.getAllMerchants();
        assertFalse(merchants.isEmpty(), "shouldnt be empty");
        assertEquals(1, merchants.size(), "should be 1");
        assertEquals(merchant, merchants.get(0), "should be same");
    }

    @Test
    void product() {
        assertNotNull(merchant.getId());
        Product product = new Product(merchant.getId(), "kladivo", "kladivo zamocnicke", 105.44, 10);
        Integer id = productService.add(product);
        assertNotNull(id, "shouldn't be null");
        product.setId(id);
        Product productFromDB = productService.get(id);
        assertEquals(product, productFromDB, "should be same");
        List<Product> products = productService.getAllProducts();
        assertFalse(products.isEmpty(), "shouldn't be empty");
        assertEquals(1, products.size(), "should be 1");
        assertEquals(product, products.get(0), "should be same");
        product.setAvailable(100);
        UpdateProductRequest request = new UpdateProductRequest(product.getName(), product.getDescription(), product.getPrice(), product.getAvailable());
        productService.update(product.getId(), request);
        Product productAfterUpdate = productService.get(product.getId());
        assertEquals(product, productAfterUpdate, "should be same");
        assertNotEquals(productFromDB, productAfterUpdate, "shouldn't be same");
        productService.delete(product.getId());
        assertEquals(0, productService.getAllProducts().size(), "shoud be empty");


    }
}
