package app.controller;

import app.domain.Customer;
import app.domain.Product;
import app.services.CustomerService;

import java.util.List;

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer save (String name){
        Customer customer = new Customer(true,name);
        return customerService.save(customer);
    }

    public List<Customer> getAll(){
        return customerService.getAllActiveCustomers();
    }

    public Customer getById(Long id){
        return customerService.getById(id);
    }

    public void updateCustomer (Long id, String newName){
        Customer customer = customerService.getById(id);
        customer.setName(newName);
        customerService.update(customer);

    }

    public void deleteCustomerById(Long id){
        customerService.deleteById(id);

    }

    public void deleteByName(String name){
        customerService.deleteByName(name);
    }

    public void restoreById(Long id){
        customerService.restoreById(id);
    }
    public long getTotalActiveCustomerCount(){
        return customerService.getTotalCountActiveCustomers();
    }

    public double getCustomerCartTotalCost(Long id){
        Customer customer = customerService.getById(id);
        return customer.getProducts().stream().mapToDouble(Product::getPrice).sum();

    }
    public  double getAverageProductPriceInCustomerCart(Long id){
        Customer customer = customerService.getById(id);
        return customer.getProducts().stream().mapToDouble(Product::getPrice).average().orElse(0.0);

    }


    public  void addProductToCustomerCart(Long customerId, Long productId){
        customerService.addProductToCart(customerId,productId);
    }

    public void removeProductFromCustomerCart(Long customerId, Long productId){
        customerService.removeProductFromCart(customerId,productId);

    }
    public void clearCustomerCart(Long id){
        customerService.clearCart(id);
    }


}
