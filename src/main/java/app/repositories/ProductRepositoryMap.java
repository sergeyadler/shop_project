package app.repositories;

import app.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductRepositoryMap implements ProductRepository{

    private final Map<Long,Product> dataBase = new HashMap<>();
    private long currentId= 0;

    @Override
    public Product save(Product product) {
        product.setId(++currentId);
        dataBase.put( currentId, product);
        return product;
    }

    @Override
    public List<Product> findAll() {
//        List<Product>activeProducts = new ArrayList<>();
//        for (Product product : dataBase.values()){
//
//                activeProducts.add(product);
//
//        }
//        return activeProducts;

        return new ArrayList<>(dataBase.values());
    }

    @Override
    public Product findById(Long id) {
//        return dataBase.values()
//                .stream()
//                .filter(product -> product.getId().equals(id))
//                .findFirst().orElse(null);
        return dataBase.get(id);
    }

    @Override
    public Product updateProduct( Product updatedProduct) {
        Long id = updatedProduct.getId();
        double newPrice = updatedProduct.getPrice();
        String newName = updatedProduct.getName();
        Product oldProduct =findById(id);
        if(oldProduct != null){
            oldProduct.setName(newName);
            oldProduct.setPrice(newPrice);
        }
        return oldProduct;
    }

    @Override
    public boolean deleteById(Long id) {


        Product oldProduct =findById(id);
        if(oldProduct == null){
            return false;
        }
        oldProduct.setActive(false);
        return true;
    }
}
