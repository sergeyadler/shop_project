package app.services;

import app.domain.Customer;
import app.domain.Product;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);

    List<Customer> getAllActiveCustomers();

    Customer getById(Long id);

    void update(Customer customer);

    void deleteById(Long id);

    void deleteByName(String name);

    void restoreById(Long id);

    long getTotalCountActiveCustomers();

    double getCustomerCartTotalCost(Long id);

    double getAverageProductPriceInCustomerCart(Long id);

    void addProductToCart(Long customerId, Long productId);

    void removeProductFromCart(Long customerId, Long productId);

    void clearCart(Long customerId);


}
