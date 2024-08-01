package controller;

import dao.impl.ProductDaoImpl;
import model.Product;
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
import java.util.List;

@WebServlet({"/delete-product", "/modify-product", "/add-product", "/admin-view", "/products"})
public class AdminServlet extends HttpServlet {
    ProductDaoImpl productDao = new ProductDaoImpl();
    private static final Logger logger = LogManager.getLogger(AdminServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/delete-product":
                Long productId = Long.valueOf(req.getParameter("productId"));
                try {
                    productDao.deleteProductById(productId);
                    resp.sendRedirect("admin-view");
                } catch (SQLException e) {
                    logger.error("Could not delete product with id {}: ", productId, e);
                    throw new RuntimeException(e);
                }
                break;
            case "/admin-view":

                try {
                    List<Product> products = productDao.getAllProducts();
                    req.setAttribute("productsList", products);
                    req.getRequestDispatcher("shopAdmin.jsp").forward(req, resp);
                } catch (SQLException e) {
                    logger.error("SQL Error: {}", e.getMessage());
                    throw new ServletException("Database error", e);
                } catch (IOException e) {
                    logger.error("IO Error: {}", e.getMessage());
                }
                break;
            case "/products":
                HttpSession session = req.getSession();
                String username = (String) session.getAttribute("username");
                try {
                    List<Product> products = productDao.getAllProducts();
                    req.setAttribute("productsList", products);
                    req.setAttribute("username", username);
                    if (username == null) {
                        req.setAttribute("logMessage", "Log In before starting shopping.");
                    }
                    req.getRequestDispatcher("products.jsp").forward(req, resp);
                } catch (SQLException e) {
                    logger.error("SQL Error: {}", e.getMessage());
                    throw new ServletException("Database error", e);
                } catch (IOException e) {
                    logger.error("IO Error: {}", e.getMessage());
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        if(action.equals("/modify-product")){
            Long id = Long.valueOf(req.getParameter("productId"));
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String image = req.getParameter("imageUrl");
            Double price = Double.valueOf(req.getParameter("price"));
            Integer stock = Integer.valueOf(req.getParameter("stock"));
            Product product = new Product(id, name, description, image, price, stock);
            try {
                productDao.modifyProduct(product);
                resp.sendRedirect("admin-view");
            } catch (SQLException e) {
                logger.error("Error: {}", e.getMessage());
            }
        }else if(action.equals("add-product")){
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String imageUrl = req.getParameter("imageUrl");
            String price = req.getParameter("price");
            String stock = req.getParameter("stock");

            if (name.isEmpty() || description.isEmpty() ||
                    imageUrl.isEmpty() || price.isEmpty() || stock.isEmpty()){
                req.setAttribute("message", "All fields must be compiled");
                RequestDispatcher dispatcher = req.getRequestDispatcher("shopAdmin.jsp");
                dispatcher.forward(req, resp);
                return;
            }

            try{
                Product product = new Product(0L, name, description, imageUrl, Double.valueOf(price), Integer.parseInt(stock));
                productDao.addProduct(product);
                resp.sendRedirect("admin-view");
            } catch (SQLException e) {
                logger.error("Error while adding product: ", e);
            }
        }
    }
}
