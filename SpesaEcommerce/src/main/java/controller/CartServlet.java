package controller;

import dao.impl.CartDaoImpl;
import dao.impl.ProductDaoImpl;
import dao.impl.UserDaoImpl;
import model.Cart;
import model.CartView;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/cart", "/modify-quantity", "/cart-view", "/delete-item"})
public class CartServlet extends HttpServlet {
    private static CartDaoImpl cartDao = new CartDaoImpl();
    private static UserDaoImpl userDao = new UserDaoImpl();
    private static ProductDaoImpl productDao = new ProductDaoImpl();
    private static final Logger logger = LogManager.getLogger(CartServlet.class);
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        if(username != null){
            long userId = (Long)session.getAttribute("userId");
            long productId = Long.parseLong(req.getParameter("productId"));
            double price = Double.parseDouble(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            double total_price = price * quantity;

            Cart cart = new Cart(0L, userId, productId, price, quantity, total_price);
            try {
                String action = req.getServletPath();
                if(action.equals("/cart")){
                    cartDao.saveCart(cart);
                    resp.sendRedirect("products");
                    logger.info("Cart saved for user ID: {}", userId);
                }else if(action.equals("/modify-quantity")){
                    cartDao.modifyCart(cart);
                    resp.sendRedirect("cart-view");
                    logger.info("Cart modified for user ID: {}", userId);
                }

            } catch (SQLException e) {
                logger.error("SQL error while processing cart for user ID: {}", userId, e);
            }
        }else{
            resp.sendRedirect("products");
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        if(action.equals("/delete-item")){
            Long cartId = Long.valueOf(req.getParameter("cartId"));
            try {
                cartDao.deleteCartByCartId(cartId);
            } catch (SQLException e) {
                logger.error("IO error while processing request for cart ID: {}", cartId, e);
            }
            resp.sendRedirect("cart-view");
        } else if (action.equals("/cart-view")) {
            HttpSession session = req.getSession();
            String username = (String) session.getAttribute("username");

            if(username != null){
                try {
                    long userId = (Long) session.getAttribute("userId");
                    List<Cart> carts = cartDao.getAllCartsForId(userId);
                    List<CartView> cartViews = new ArrayList<>();

                    double somma =0;
                    for (Cart cart : carts) {
                        String prodName = productDao.getProductById(cart.getProductId()).getName();
                        CartView cartView = new CartView(cart.getCartId(), cart.getUserId(), cart.getProductId(),
                                prodName, cart.getUnitPrice(), cart.getQuantity(), cart.getTotalPrice());
                        cartViews.add(cartView);
                        somma += cart.getTotalPrice();
                    }
                    req.setAttribute("orderTotal", somma);
                    req.setAttribute("carts", cartViews);


                    User user = userDao.getUserById(userId);
                    req.setAttribute("user", user);
                    req.setAttribute("username", username);

                    req.getRequestDispatcher("cart.jsp").forward(req, resp);
                } catch (SQLException | IOException e) {
                    logger.error("Error: {}", e.getMessage());
                }
            }else {
                resp.sendRedirect("login");
            }
        }
    }
}
