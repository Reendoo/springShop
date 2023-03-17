package sk.peterrendek.learn2code.springshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.peterrendek.learn2code.springshop.db.services.ProductService;
import sk.peterrendek.learn2code.springshop.db.services.impl.ProductServiceImpl;
import sk.peterrendek.learn2code.springshop.db.services.request.UpdateProductRequest;
import sk.peterrendek.learn2code.springshop.domain.Merchant;
import sk.peterrendek.learn2code.springshop.domain.Product;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    ProductService service;


    public ProductController(ProductService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity add(@RequestBody Product p){
        System.out.println(p);
        Integer id = service.add(p);
        if(id!=null){
            return new ResponseEntity(id,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(id,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping
    public ResponseEntity getAll(){
        List<Product> list = service.getAllProducts();
        return new ResponseEntity<>(list, HttpStatus.OK);//code 200
    }
    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") int id){
        Product p = service.get(id);
        if(p!=null){
            return new ResponseEntity<>(p,HttpStatus.OK); //code 200
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND); //code 404
    }
    @PatchMapping("{id}")
    public  ResponseEntity update(@RequestBody UpdateProductRequest updateProductRequest, @PathVariable("id") int id){
        if(service.get(id)!=null){
            service.update(id,updateProductRequest);
            return ResponseEntity.ok().build(); //code 200
        }else{
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED) //Code 412
                    .body("Product with id: "+id+" doesnt exist");
        }
    }    @DeleteMapping("{id}")
    public ResponseEntity delete (@PathVariable("id") int id){
        if(service.get(id)!=null){
            service.delete(id);
            return ResponseEntity.ok().build(); //code 200
        }else{
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED) // Code 412
                    .body("Product with id: "+id+" doesn't exist");
        }
    }
}
