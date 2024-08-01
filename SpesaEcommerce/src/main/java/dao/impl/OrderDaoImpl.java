package dao.impl;

import dao.OrderDao;
import model.Cart;
import model.Order;
import model.OrderItems;
import util.DatabaseUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class OrderDaoImpl implements OrderDao {
    private Connection connection;

    private static final String
            INSERT_ORDER = "INSERT INTO Orders (order_date, user_id, address, total_price) VALUES (?, ?, ?, ?)",
            INSERT_ORDER_ITEMS = "INSERT INTO order_items (order_id, user_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?, ?)",
            DELETE_FROM_CART = "DELETE FROM cart WHERE user_id=? AND product_id=?",
            SELECT_ORDER_PRODUCTS_BY_ID = "SELECT oi.order_id, oi.user_id, oi.product_id, oi.quantity, oi.unit_price, pr.name as product_name " +
            "FROM order_items AS oi " +
            "JOIN product AS pr ON oi.product_id = pr.product_id " +
            "WHERE oi.order_id = ?",
            SELECT_ALL_ORDERS_BY_ID = "SELECT * FROM orders WHERE user_id=?";

    public OrderDaoImpl() {
        try {
            this.connection = DatabaseUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long insertOrder(Order order) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, String.valueOf(order.getOrderDate()));
            statement.setLong(2, order.getUserId());
            statement.setString(3, order.getAddress());
            statement.setDouble(4, order.getTotalPrice());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Inserting order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertOrderItems(Long orderId, Cart cart) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_ITEMS)) {
            statement.setLong(1, orderId);
            statement.setLong(2, cart.getUserId());
            statement.setLong(3, cart.getProductId());
            statement.setLong(4, cart.getQuantity());
            statement.setDouble(5, cart.getUnitPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement statement = connection.prepareStatement(DELETE_FROM_CART)) {
            statement.setLong(1, cart.getUserId());
            statement.setLong(2, cart.getProductId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderItems> getOrderProductsById(Long orderId) throws SQLException {
        List<OrderItems> orderItems = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_PRODUCTS_BY_ID)) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderItems.add(mapToOrderItems(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderItems;
    }

    @Override
    public List<Order> getAllOrdersById(long userId) throws SQLException {
        List<Order> allOrders = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ORDERS_BY_ID)) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = mapToOrder(rs);
                allOrders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allOrders;
    }

    @Override
    public OrderItems mapToOrderItems(ResultSet resultSet) throws SQLException {
        OrderItems orderI = new OrderItems();
        orderI.setOrderId(resultSet.getLong("order_id"));
        orderI.setUserId(resultSet.getLong("user_id"));
        orderI.setProductId(resultSet.getLong("product_id"));
        orderI.setProductName(resultSet.getString("product_name"));
        orderI.setQuantity(resultSet.getInt("quantity"));
        orderI.setUnitPrice(resultSet.getDouble("unit_price"));
        return orderI;
    }

    @Override
    public Order mapToOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getLong("order_id"));
        order.setOrderDate(LocalDate.parse(resultSet.getString("order_date")));
        order.setUserId(resultSet.getLong("user_id"));
        order.setAddress(resultSet.getString("address"));
        order.setTotalPrice(resultSet.getDouble("total_price"));
        return order;
    }
}
