package org.vital.services;

import org.springframework.stereotype.Service;
import org.vital.hibdao.ProductDao;
import org.vital.models.Product;
import org.vital.models.User;

import java.util.List;

@Service
public class ProductService {
    private ProductDao productDao = new ProductDao();

    public ProductService() {
    }

    public Product findProduct(int id) {
        return productDao.findById(id);
    }

    public void saveProduct(Product product) {
        productDao.save(product);
    }

    /*public void deleteUser(User user) {
        usersDao.delete(user);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }*/

    public List<Product> findAllProducts() {
        return productDao.findAll();
    }
}
