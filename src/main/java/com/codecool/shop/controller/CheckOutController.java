package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import ognl.enhance.OrderedReturn;
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
import java.util.Enumeration;
import java.util.Map;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckOutController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(CheckOutController.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("product/checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Cart cart = productDataStore.getCart();

        Map<String, String[]> paramMap = req.getParameterMap();
        System.out.println(paramMap);
        Address billingInfo = new Address(paramMap.get("bcountry")[0], paramMap.get("bcity")[0], paramMap.get("bzip")[0], paramMap.get("baddress")[0]);
        Address shippingInfo = new Address(paramMap.get("scountry")[0], paramMap.get("scity")[0], paramMap.get("szip")[0], paramMap.get("saddress")[0]);
        Order newOrder = new Order(cart, paramMap.get("name")[0], paramMap.get("email")[0], paramMap.get("phone")[0], shippingInfo, billingInfo);
        context.setVariable("order", newOrder);
        if (newOrder.allFieldsValid()) {
            if(billingInfo.equals(shippingInfo)){
                if (!addressDataStore.isAlreadyInDb(billingInfo)){
                    addressDataStore.add(billingInfo);
                    shippingInfo.setId(billingInfo.getId());
                    }
                else {
                    int id = addressDataStore.find(billingInfo);
                    billingInfo.setId(id);
                    shippingInfo.setId(id); }
                }
            else {
                if (!addressDataStore.isAlreadyInDb(billingInfo)){
                    addressDataStore.add(newOrder.getBillingAddress());
                    System.out.println(newOrder.getShippingAddress());
                } else{
                    int id = addressDataStore.find(billingInfo);
                    billingInfo.setId(id);
                }
                if (!addressDataStore.isAlreadyInDb(shippingInfo)){
                    addressDataStore.add(newOrder.getShippingAddress());
                } else {
                    int id = addressDataStore.find(shippingInfo);
                    shippingInfo.setId(id);
                }
            }
            orderDataStore.add(newOrder);
            logger.info("Added a new order on id {}" , newOrder.getId());
            resp.sendRedirect(req.getContextPath() + "/payment");

        } else {
            logger.info("Failed to add a new order(one or more inputs invalid)");
            engine.process("product/checkout.html", context, resp.getWriter());
        }
    }
}
