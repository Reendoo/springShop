package sk.peterrendek.learn2code.springshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.peterrendek.learn2code.springshop.db.services.api.CustomerAccountService;
import sk.peterrendek.learn2code.springshop.db.services.api.CustomerService;
import sk.peterrendek.learn2code.springshop.domain.Customer;
import sk.peterrendek.learn2code.springshop.domain.CustomerAccount;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerAccountService customerAccountService;

    public CustomerController(CustomerService service,CustomerAccountService customerAccountService) {
        this.customerService = service;
        this.customerAccountService=customerAccountService;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Customer c) {
        Integer id = customerService.add(c);
        if (id != null) {
            return new ResponseEntity<>(id, HttpStatus.CREATED); //code 201
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // code 500
    }
    @GetMapping
    public ResponseEntity getAll() {
        List<Customer> list = customerService.getAllCustomers();
        return new ResponseEntity<>(list, HttpStatus.OK);  //code 200
    }
    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") int id) {
        Customer c = customerService.get(id);
        if (c != null) {
            return new ResponseEntity<>(c, HttpStatus.OK);  //code 200
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  //code 404
    }
    @PostMapping("/account")
    public ResponseEntity addAccount(@RequestBody CustomerAccount customerAccount){
        customerAccountService.addCustomerAccount(customerAccount);
        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }



}
