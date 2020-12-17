package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/cart"})
public class CartController  extends Controller {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    public CartController(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = productDataStore.getCart();
        if (req.getParameter("addid") != null){
        int id = Integer.parseInt(req.getParameter("addid"));
            cart.add(productDataStore.find(id));
            logger.info("Added a(n) {} (id:{}) to cart", productDataStore.find(id).getName(), id );
        }
        else if(req.getParameter("remid") != null){
            int id = Integer.parseInt(req.getParameter("remid"));
            cart.remove(cart.getItemById(id));
            logger.info("Removed a(n) {} (id:{}) from cart", productDataStore.find(id).getName(), id );
        }
        else if(req.getParameter("delid") != null){
            int id = Integer.parseInt(req.getParameter("delid"));
            cart.delete(cart.getItemById(id));
            logger.info("Deleted every {} (id:{}) from cart", productDataStore.find(id).getName(), id );
        }
//        resp.sendRedirect(req.getContextPath() + "/redirected");

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException{
        doGet(request, res);
    }
}
