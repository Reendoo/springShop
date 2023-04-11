package sk.peterrendek.learn2code.springshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.peterrendek.learn2code.springshop.db.services.api.BoughtProductService;
import sk.peterrendek.learn2code.springshop.domain.BoughtProduct;

import java.util.List;

@RestController
@RequestMapping("bought-product/")
public class BoughtProductController {
    private final BoughtProductService boughtProductService;

    public BoughtProductController(BoughtProductService boughtProductService) {
        this.boughtProductService = boughtProductService;
    }

    @GetMapping("{customer_id}")
    public ResponseEntity get(@PathVariable int customer_id) {
        List<BoughtProduct> list = boughtProductService.getAllCustomersById(customer_id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
