package sk.peterrendek.learn2code.springshop;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import sk.peterrendek.learn2code.springshop.db.services.request.UpdateProductRequest;
import sk.peterrendek.learn2code.springshop.domain.Customer;
import sk.peterrendek.learn2code.springshop.domain.Merchant;
import sk.peterrendek.learn2code.springshop.domain.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureMockMvc
public class RestControllerTests {
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    Merchant m;

    @Test
    void customer() throws Exception {
        Customer c = new Customer("Fero", "Mrkvicka", "fero@gmail.com", "adresa", 20, "telefon");

        //add customer
        String id = insertEntity(c, "/customer");
        c.setId(objectMapper.readValue(id, Integer.class));

        String customerJSON = selectEntityOK("/customer/" + c.getId());
        Customer cFromDB = objectMapper.readValue(customerJSON, Customer.class);
        assertEquals(c, cFromDB, "should be same");
        String customersJSON = selectEntityOK("/customer");
        List<Customer> list = objectMapper.readValue(customersJSON, new TypeReference<>() {
        }); // retype
        assertEquals(list.size(), 1, "should be 1");
        assertEquals(c, list.get(0), "should be same");
        assertEquals(cFromDB, list.get(0), "should be same");
    }

    private String selectEntityOK(String url) throws Exception {
        return mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
    private String selectEntityNotOK(String url) throws Exception {
        return mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();
    }

    private String insertEntity(Object c, String url) throws Exception {
        return mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(c)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void merchant() throws Exception {
        //merchant was already created in init
        String merchantJSON = selectEntityOK("/merchant/" + m.getId());
        Merchant mFromDB = objectMapper.readValue(merchantJSON, Merchant.class);
        assertEquals(m, mFromDB, "should be same");
        String merchantsJSON = selectEntityOK("/merchant");
        List<Merchant> merchants = objectMapper.readValue(merchantsJSON, new TypeReference<>() {
        });
        assertEquals(1, merchants.size(), "should be 1");
        assertEquals(m, merchants.get(0), "should be same");
        assertEquals(mFromDB, merchants.get(0), "should be same");
    }

    @Test
    void product() throws Exception {
        if (m.getId() == null) throw new AssertionError();
        Product p = new Product(m.getId(), "kladivo", "superKladivo", 42.3, 100);
        String id = insertEntity(p, "/product");
        p.setId(objectMapper.readValue(id, Integer.class));
        String productJSON = selectEntityOK("/product/" + p.getId());
        Product pFromDB = objectMapper.readValue(productJSON, Product.class);
        assertEquals(p, pFromDB, "should be same");
        String productsJSON = selectEntityOK("/product");
        List<Product> list = objectMapper.readValue(productsJSON, new TypeReference<>() {
        }); // retype
        assertEquals(list.size(), 1, "should be 1");
        assertEquals(p, list.get(0), "should be same");
        assertEquals(pFromDB, list.get(0), "should be same");

        UpdateProductRequest request = new UpdateProductRequest(p.getName(), p.getDescription(), 9999.99, 1500);
        update(request, "/product/" + p.getId());


        String pUpdatedJSON = selectEntityOK("/product/" + p.getId());
        Product pUpdated = objectMapper.readValue(pUpdatedJSON, Product.class);

        checkForUpdate(p, list, request, pUpdated);

        mockMvc.perform(delete("/product/" + p.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        String pDeletedJSON = selectEntityNotOK("/product/" + p.getId());
        String productsDeletedJSON = selectEntityOK("/product");
        List<Product> listDeleted = objectMapper.readValue(productsDeletedJSON, new TypeReference<>() {
        }); // retype

        assertEquals(0,listDeleted.size(), "should be empty");

        mockMvc.perform(delete("/product/" + p.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isPreconditionFailed());



    }

    private static void checkForUpdate(Product p, List<Product> list, UpdateProductRequest request, Product pFromDB2) {
        assertNotEquals(p, pFromDB2, "shouldn't be same");
        assertNotEquals(list.get(0), pFromDB2, "shouldn't be same");
        assertEquals(p.getId(), pFromDB2.getId(), "should be same");
        assertEquals(p.getDescription(), pFromDB2.getDescription(), "should be same");
        assertEquals(p.getName(), pFromDB2.getName(), "should be same");
        assertNotEquals(p.getPrice(), pFromDB2.getPrice(), "shouldn't be same");
        assertNotEquals(p.getAvailable(), (pFromDB2.getAvailable()), "shouldn't be same");
        assertEquals(request.getPrice(), pFromDB2.getPrice(), "shouldn't be same");
        assertEquals(request.getAvailable(), (pFromDB2.getAvailable()), "shouldn't be same");
    }

    private String update(UpdateProductRequest request, String url) throws Exception {
        return mockMvc.perform(patch(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @BeforeEach
    public void init() throws Exception {
        if (m == null) {
            m = new Merchant("Rendo", "rendo@gmail.com", "Sittard");
            String id = insertEntity(m, "/merchant");
            m.setId(objectMapper.readValue(id, Integer.class));
        }
    }
}
