package app.services;

import app.domain.Product;

import java.util.List;

public interface ProductService {
//    Product save(Product product);
//
//    List<Product> getAllActiveProducts();
//
//    Product getActiveProductById(Long Id);
//
//    Product updateProductById(Long Id, Product updatedProduct);
//
//    boolean deleteProductById(Long Id);
//
//    boolean deleteProductByName(String name);
//
//    boolean restoreProductById(Long Id);
//
//    long countActicveProducts();
//
//    double sumActiveProducts();
//
//    double averageActiveProductPrice();
    Product save(Product product);

    List<Product> getAllActiveProducts();

    Product getById(Long id);

    void update(Product product);

    void deleteById(Long id);

    void deleteByName(String name);

    void restoreById(Long id);

    long getActiveProductsTotalCount();

    double getActiveProductsTotalCost();

    double getActiveProductsAveragePrice();


}
