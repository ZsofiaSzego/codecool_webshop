package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.PaymentDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.PaymentDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends Controller{

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    public PaymentController(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("product/payment.html", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Payment newPayment;
        Map<String, String[]> paramMap = req.getParameterMap();
        if (paramMap.get("radiobtn") == null){ engine.process("product/payment.html", context, resp.getWriter()); }

        newPayment = new Payment(paramMap.get("radiobtn")[0], paramMap.get("cardnumber")[0], paramMap.get("cardholder")[0],
                paramMap.get("expm")[0], paramMap.get("expyear")[0] );
        newPayment.setCreditCardCode(paramMap.get("code")[0]);
        newPayment.setPayPalUserName(paramMap.get("username")[0]);
        newPayment.setPayPalPassword(paramMap.get("password")[0]);
            context.setVariable("payment", newPayment);

        if (newPayment.allFieldsValid()){
            paymentDataStore.add(newPayment);
            logger.info("Payment (id:{}) initialized", newPayment.getId());
                    resp.sendRedirect(req.getContextPath() + "/redirected");
        } else {
            logger.info("Failed payment(Invalid input(s))");
            engine.process("product/payment.html", context, resp.getWriter());
        }
    }
}
