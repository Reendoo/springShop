package sk.peterrendek.learn2code.springshop.db.services.impl;

import org.springframework.stereotype.Service;
import sk.peterrendek.learn2code.springshop.db.services.api.*;
import sk.peterrendek.learn2code.springshop.db.services.api.request.BuyProductRequest;
import sk.peterrendek.learn2code.springshop.db.services.api.response.BuyProductResponse;
import sk.peterrendek.learn2code.springshop.domain.BoughtProduct;
import sk.peterrendek.learn2code.springshop.domain.Customer;
import sk.peterrendek.learn2code.springshop.domain.Product;

@Service
public class ShoppingServiceImpl implements ShoppingService {
    private final ProductService productService;
    private final CustomerService customerService;
    private final CustomerAccountService customerAccountService;
    private final BoughtProductService boughtProductService;

    public ShoppingServiceImpl(ProductService productService, CustomerService customerService, CustomerAccountService customerAccountService, BoughtProductService boughtProductService) {
        this.productService = productService;
        this.customerService = customerService;
        this.customerAccountService = customerAccountService;
        this.boughtProductService = boughtProductService;
    }

    @Override
    public BuyProductResponse buyProduct(BuyProductRequest req) {
        int product_id = req.product_id();
        int customer_id = req.customer_id();

        Product product = productService.get(product_id);
        if (product == null) {
            return new BuyProductResponse(false, "Product with id: " + product_id + " doesn't exist");
        }

        Customer customer = customerService.get(customer_id);
        if (customer == null) {
            return new BuyProductResponse(false, "Customer with id: " + customer_id + " doesn't exist");
        }

        if(product.getAvailable() < req.quantity()){
            return new BuyProductResponse(false, "Products with id: " + product_id + " aren't enough in stock");
        }

        Double customerMoney = customerAccountService.getMoney(customer_id);
        if(customerMoney == null){
            return new BuyProductResponse(false, "Customer with id:" + customer_id + " doesn't have account");
        }else{
            double totalPrice = product.getPrice()* req.quantity();
            if(customerMoney>=totalPrice){
                productService.updateAvailableInternal(product_id,product.getAvailable()- req.quantity());
                customerAccountService.setMoney(customer_id,customerMoney-totalPrice);
                boughtProductService.add(new BoughtProduct(product_id,customer_id,req.quantity()));
                return new BuyProductResponse(true);
            }else{
                return new BuyProductResponse(false, "Customer with id:" + customer_id + " doesn't have enough money on account");
            }
        }
    }
}
