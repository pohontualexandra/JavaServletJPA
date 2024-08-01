package dao.impl;

import dao.ProductDao;
import model.Product;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private Connection connection;

    private static final String ADD_PRODUCT = "INSERT into product (name, description, image_url, price, stock) VALUES (?, ?, ?, ?, ?)",
            DELETE_FROM_CART = "DELETE FROM cart WHERE product_id=?",
            DELETE_PRODUCT_BY_ID = "DELETE FROM product WHERE product_id = ?",
            MODIFY_PRODUCT = "UPDATE product SET name=?, description=?, image_url=?, price=?, stock=? WHERE product_id=?",
            GET_PRODUCT_BY_ID = "SELECT * from product WHERE product_id = ?",
            GET_ALL_PRODUCTS = "SELECT * from product ORDER BY product_id";

    public ProductDaoImpl(){
        try {
            this.connection = DatabaseUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setString(3, product.getImageUrl());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getStock());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProductById(Long productId) throws SQLException {
        String[] sqlStatements = {DELETE_FROM_CART, DELETE_PRODUCT_BY_ID};

        for (String sql : sqlStatements) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, productId);
                statement.executeUpdate();
            }
        }
    }

    @Override
    public void modifyProduct(Product product) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(MODIFY_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setString(3, product.getImageUrl());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getStock());
            statement.setLong(6, product.getProductId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProductById(Long productId) throws SQLException {
        Product product = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
            statement.setLong(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setProductId(resultSet.getLong("product_id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setImageUrl(resultSet.getString("image_url"));
                product.setPrice(resultSet.getDouble("price"));
                product.setStock(resultSet.getInt("stock"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_PRODUCTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getLong("product_id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setImageUrl(resultSet.getString("image_url"));
                product.setPrice(resultSet.getDouble("price"));
                product.setStock(resultSet.getInt("stock"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
