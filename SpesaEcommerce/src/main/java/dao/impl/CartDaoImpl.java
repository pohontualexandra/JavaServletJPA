package dao.impl;

import dao.CartDao;
import model.Cart;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private Connection connection;

    private static final String
            SELECT_CART_BY_USER_AND_PRODUCT = "SELECT * FROM cart WHERE user_id = ? AND product_id = ?",
            INSERT_CART = "INSERT INTO cart (user_id, product_id, unit_price, quantity, total_price) VALUES (?, ?, ?, ?, ?)",
            UPDATE_CART_QUANTITY = "UPDATE cart SET quantity = ?, total_price = ? WHERE product_id = ?",
            SELECT_CARTS_BY_USER_ID = "SELECT * FROM cart WHERE user_id = ?",
            DELETE_CART_BY_CART_ID = "DELETE FROM cart WHERE cart_id = ?";

    public CartDaoImpl() {
        try {
            this.connection = DatabaseUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifyCart(Cart cart) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CART_BY_USER_AND_PRODUCT)) {
            statement.setLong(1, cart.getUserId());
            statement.setLong(2, cart.getProductId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Cart cart_found = mapCart(rs);
                cart_found.setQuantity(cart.getQuantity());
                cart_found.setTotalPrice(cart.getTotalPrice());
                updateCartQuantity(cart_found);
            }
        }
    }

    @Override
    public void saveCart(Cart cart) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CART_BY_USER_AND_PRODUCT)) {
            statement.setLong(1, cart.getUserId());
            statement.setLong(2, cart.getProductId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Cart existingCart = mapCart(resultSet);
                existingCart.setQuantity(existingCart.getQuantity() + cart.getQuantity());
                existingCart.setTotalPrice(existingCart.getTotalPrice() + cart.getTotalPrice());
                updateCartQuantity(existingCart);
            } else {
                try (PreparedStatement pstatement = connection.prepareStatement(INSERT_CART)) {
                    pstatement.setLong(1, cart.getUserId());
                    pstatement.setLong(2, cart.getProductId());
                    pstatement.setDouble(3, cart.getUnitPrice());
                    pstatement.setInt(4, cart.getQuantity());
                    pstatement.setDouble(5, cart.getTotalPrice());
                    pstatement.executeUpdate();
                }
            }
        }
    }

    @Override
    public void updateCartQuantity(Cart cart) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_CART_QUANTITY)) {
            statement.setInt(1, cart.getQuantity());
            statement.setDouble(2, cart.getTotalPrice());
            statement.setLong(3, cart.getProductId());
            statement.executeUpdate();
        }
    }

    @Override
    public List<Cart> getAllCartsForId(long idUser) throws SQLException {
        List<Cart> carts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CARTS_BY_USER_ID)) {
            statement.setLong(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                carts.add(mapCart(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Carts: " + carts);
        return carts;
    }

    @Override
    public void deleteCartByCartId(Long cartId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_CART_BY_CART_ID)) {
            statement.setLong(1, cartId);
            statement.executeUpdate();
        }
    }

    private Cart mapCart(ResultSet resultSet) throws SQLException {
        Cart cart = new Cart();
        cart.setCartId(resultSet.getLong("cart_id"));
        cart.setUserId(resultSet.getLong("user_id"));
        cart.setProductId(resultSet.getLong("product_id"));
        cart.setUnitPrice(resultSet.getDouble("unit_price"));
        cart.setQuantity(resultSet.getInt("quantity"));
        cart.setTotalPrice(resultSet.getDouble("total_price"));
        return cart;
    }
}
