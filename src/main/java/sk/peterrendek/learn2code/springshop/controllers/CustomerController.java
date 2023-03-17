package sk.peterrendek.learn2code.springshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.peterrendek.learn2code.springshop.db.services.CustomerService;
import sk.peterrendek.learn2code.springshop.domain.Customer;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Customer c) {
        Integer id = service.add(c);
        if (id != null) {
            return new ResponseEntity<>(id, HttpStatus.CREATED); //code 201
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // code 500
    }
    @GetMapping
    public ResponseEntity getAll() {
        List<Customer> list = service.getAllCustomers();
        return new ResponseEntity<>(list, HttpStatus.OK);  //code 200
    }
    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") int id) {
        Customer c = service.get(id);
        if (c != null) {
            return new ResponseEntity<>(c, HttpStatus.OK);  //code 200
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  //code 404
    }


}
