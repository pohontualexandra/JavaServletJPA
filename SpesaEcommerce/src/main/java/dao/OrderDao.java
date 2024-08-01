package dao;

import model.Cart;
import model.Order;
import model.OrderItems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface OrderDao {
    long insertOrder(Order order) throws SQLException;

    void insertOrderItems(Long orderId, Cart cart) throws SQLException;

    List<OrderItems> getOrderProductsById(Long orderId) throws SQLException;

    List<Order> getAllOrdersById(long userId) throws SQLException;

    OrderItems mapToOrderItems(ResultSet resultSet) throws SQLException;

    Order mapToOrder(ResultSet resultSet) throws SQLException;
}
