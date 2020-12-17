package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends Controller {

    public ProductController(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        productDataStore = ProductDaoMem.getInstance();
//        productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//        supplierDataStore = SupplierDaoMem.getInstance();



        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<Product> productList;
        if (req.getParameter("category") == null || req.getParameter("supplier") == null) {
            productList = productDataStore.getAll();
        } else {
            int category = Integer.parseInt(req.getParameter("category"));
            int supplier = Integer.parseInt(req.getParameter("supplier"));
            if (category == 0 && supplier == 0){
                productList = productDataStore.getAll();
            }
            if (!(category == 0)) {
                if (!(supplier == 0)) {
                    productList = productDataStore.getBy(productCategoryDataStore.find(category), supplierDataStore.find(supplier));
                } else {
                    productList = productDataStore.getBy(productCategoryDataStore.find(category));
                }
            }
            else {
                if (!(category == 0)) {
                    productList = productDataStore.getBy(productCategoryDataStore.find(category), supplierDataStore.find(supplier));
                } else {
                    productList = productDataStore.getBy(supplierDataStore.find(supplier));
                }
            }
        }
        context.setVariable("products", productList);
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("cart", productDataStore.getCart());
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }

}
