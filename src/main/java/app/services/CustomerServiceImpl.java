package app.services;

import app.domain.Customer;
import app.domain.Product;
import app.exceptions.*;
import app.repositories.CustomerRepository;
import app.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository repository;
    private final ProductRepository productRepository;


    public CustomerServiceImpl(CustomerRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    @Override
    public Customer save(Customer customer) {
        if(customer == null){
            throw new CustomerSaveException("Customer cannot be null");
        }
        String name = customer.getName();
        if (name == null || name.trim().isEmpty() || name.length() < 3) {
            throw new ProductSaveException("Customer name should be at least 3 characters long");
        }

        customer.setActive(true);
        return repository.save(customer);
    }

    @Override
    public List<Customer> getAllActiveCustomers() {
        return repository.findAll()
                .stream().
                filter(Customer::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public Customer getById(Long id) {
        Customer customer = repository.findById(id);
        if(customer == null || !customer.isActive()){
            throw new CustomerNotFoundException("Customer with id "+ id+ " Not Found");
        }
        return customer;
    }

    @Override
    public void update(Customer customer) {
        if(customer == null){
            throw new CustomerUpdateException("Customer cannot be null");
        }
        Long id = customer.getId();
        if(id == null || id < 0){
            throw new CustomerUpdateException("Customer ID should be positive");
        }
        String name = customer.getName();
        if (name == null || name.trim().isEmpty() || name.length() < 3) {
            throw new CustomerUpdateException("Customer name should be at least 3 characters long");
        }
        repository.update(customer);
    }

    @Override
    public void deleteById(Long id) {
        getById(id).setActive(false);
    }

    @Override
    public void deleteByName(String name) {
        Customer customer = getAllActiveCustomers()
                .stream()
                .filter(c-> c.getName().equals(name))
                .findFirst()
                .orElse(null);
        if(customer == null){
            throw new CustomerNotFoundException("Customer with name "+ name + " not found");

        }
        customer.setActive(false);
    }

    @Override
    public void restoreById(Long id) {
        Customer customer = repository.findById(id);
        if(customer == null){
            throw new CustomerNotFoundException("Customer with id "+ id+ " Not Found");
        }
        customer.setActive(true);
    }

    @Override
    public long getTotalCountActiveCustomers() {
        return getAllActiveCustomers().size();
    }

    @Override
    public double getCustomerCartTotalCost(Long id) {
        Customer customer = getById(id);
        return customer.getProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    @Override
    public double getAverageProductPriceInCustomerCart(Long id) {
       Customer customer = getById(id);
       return customer.getProducts()
               .stream()
               .mapToDouble(Product::getPrice)
               .average()
               .orElse(0.0);
    }

    @Override
    public void addProductToCart(Long customerId, Long productId) {
        Customer customer = getById(customerId);
        Product product = productRepository.findById(productId);

        if(product == null || !product.isActive()){
            throw new ProductNotFoundException("Product with id "+ productId + " not found");

        }
        customer.getProducts().add(product);
    }

    @Override
    public void removeProductFromCart(Long customerId, Long productId) {
        Customer customer = getById(customerId);
        customer.getProducts().removeIf(product -> product.getId().equals(productId));

    }

    @Override
    public void clearCart(Long customerId) {
        Customer customer = getById(customerId);
        customer.getProducts().clear();

    }
}
