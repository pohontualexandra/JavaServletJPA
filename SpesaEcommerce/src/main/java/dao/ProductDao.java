package dao;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    void addProduct(Product product) throws SQLException;

    void deleteProductById(Long cartId) throws SQLException;

    void modifyProduct(Product product) throws SQLException;

    Product getProductById(Long productId) throws SQLException;
    List<Product> getAllProducts() throws SQLException;
}

