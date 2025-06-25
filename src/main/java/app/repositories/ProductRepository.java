package app.repositories;

import app.domain.Product;

import java.util.List;

public interface ProductRepository {
    //CRUD - Create, Read, Update, Delete

    Product save(Product product);

    List<Product> findAll();

    Product findById(Long id);

    Product updateProduct(Product updatedProduct);

    boolean deleteById(Long id);


}
