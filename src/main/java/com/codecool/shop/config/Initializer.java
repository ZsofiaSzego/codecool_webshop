package com.codecool.shop.config;

import com.codecool.shop.controller.Controller;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

@WebListener
public class Initializer implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(Initializer.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        DatabaseManager databaseManager = new DatabaseManager();
        try {
            DataSource dataSource = databaseManager.setup();
            Controller controller = new Controller(dataSource);
            Cart cart = new Cart();
            controller.getProductDataStore().setCart(cart);
        } catch (SQLException | IOException throwable) {
            throwable.printStackTrace();
        }

        logger.info("Context initialized");
    }
}
