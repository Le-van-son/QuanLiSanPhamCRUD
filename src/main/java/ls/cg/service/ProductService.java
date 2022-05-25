package ls.cg.service;

import ls.cg.model.Product;

import java.util.List;

public interface ProductService {
    List<Product>finAll();
    void save (Product product);
    int findIndexById(int id);
    Product findById(int id);
    List<Product> findByName(String name);
    void update(int id, Product product);
    void delete (int id);
}
