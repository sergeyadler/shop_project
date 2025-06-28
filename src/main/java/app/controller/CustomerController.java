package app.controller;

import app.domain.Customer;
import app.services.CustomerService;

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer save (String name){
        Customer customer = new Customer(true,name);
        return customerService.save(customer);
    }

}
