package dao;

import model.Cart;

import java.sql.SQLException;
import java.util.List;

public interface CartDao {
    void modifyCart(Cart cart) throws SQLException;

    void saveCart(Cart cart) throws SQLException;

    void updateCartQuantity(Cart cart) throws SQLException;

    List<Cart> getAllCartsForId(long idUser) throws SQLException;

    void deleteCartByCartId(Long cartId) throws SQLException;
}
