package controller;


import dao.impl.CartDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.UserDaoImpl;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet({"/orders-history", "/place-order"})
public class OrderServlet extends HttpServlet {

    UserDaoImpl userDao = new UserDaoImpl();
    CartDaoImpl cartDao = new CartDaoImpl();
    OrderDaoImpl orderDao = new OrderDaoImpl();
    private static final Logger logger = LogManager.getLogger(OrderServlet.class);

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            long userId = (Long) session.getAttribute("userId");

            User user = userDao.getUserById(userId);
            List<Cart> carts = cartDao.getAllCartsForId(userId);

            double somma = 0;
            for (Cart cart : carts) {
                somma += cart.getTotalPrice();
            }

            String address = user.getAddress();
            Double totalPrice = somma;
            LocalDate date = LocalDate.now();

            Order order = new Order(userId, date, address, totalPrice);
            long orderId = orderDao.insertOrder(order);
            order.setOrderId(orderId);

            session.setAttribute("currentOrderId", orderId);

            for (Cart cart : carts) {
                orderDao.insertOrderItems(orderId, cart);
            }

            List<OrderItems> orderItems = orderDao.getOrderProductsById(orderId);
            Order currentOrder = new Order(orderId, userId, date, address, totalPrice);

            req.setAttribute("orderItems", orderItems);
            req.setAttribute("user", user);
            req.setAttribute("currentOrder", currentOrder);
            req.setAttribute("cartsList", carts);

            RequestDispatcher dispatcher = req.getRequestDispatcher("orderView.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            logger.error("Error: {}", e.getMessage());
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        if(username != null){
            try {
                long userId = (Long) session.getAttribute("userId");
                List<Order> ordersHistory = orderDao.getAllOrdersById(userId);

                req.setAttribute("ordersHistory", ordersHistory);
                RequestDispatcher dispatcher = req.getRequestDispatcher("orderHistory.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException | IOException e) {
                logger.error("Error: {}", e.getMessage());
            }
        }else {
            resp.sendRedirect("login");
        }
    }
}
