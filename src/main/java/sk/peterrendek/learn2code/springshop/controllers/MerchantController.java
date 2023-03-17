package sk.peterrendek.learn2code.springshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.peterrendek.learn2code.springshop.db.services.MerchantService;
import sk.peterrendek.learn2code.springshop.domain.Customer;
import sk.peterrendek.learn2code.springshop.domain.Merchant;

import java.util.List;

@RestController
@RequestMapping("merchant")
public class MerchantController {
    MerchantService service;

    public MerchantController(MerchantService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Merchant m){
        Integer id = service.add(m);
        if (id != null) {
            return new ResponseEntity<>(id, HttpStatus.CREATED);  // code 201
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); //code 500
    }
    @GetMapping
    public ResponseEntity getAll(){
        List<Merchant> list = service.getAllMerchants();
        return new ResponseEntity<>(list, HttpStatus.OK); //code 200
    }
    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") int id){
        Merchant m = service.get(id);
        if(m!=null){
            return new ResponseEntity<>(m,HttpStatus.OK); //code 202
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND); //code 404
    }


}
