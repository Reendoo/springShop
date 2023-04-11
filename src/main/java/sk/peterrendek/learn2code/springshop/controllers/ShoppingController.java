package sk.peterrendek.learn2code.springshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.peterrendek.learn2code.springshop.db.services.api.ShoppingService;
import sk.peterrendek.learn2code.springshop.db.services.api.request.BuyProductRequest;
import sk.peterrendek.learn2code.springshop.db.services.api.response.BuyProductResponse;

@RestController
@RequestMapping("shop")
public class ShoppingController {
    private final ShoppingService service;

    public ShoppingController(ShoppingService service) {
        this.service = service;
    }

    @PostMapping("/buy")
    public ResponseEntity buy(@RequestBody BuyProductRequest request) {
        BuyProductResponse response = service.buyProduct(request);
        if (response.isSucces()) {
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(response.getErrorMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
}
