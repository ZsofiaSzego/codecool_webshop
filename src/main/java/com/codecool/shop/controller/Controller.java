package com.codecool.shop.controller;

import com.codecool.shop.dao.*;

import javax.servlet.http.HttpServlet;

public class Controller extends HttpServlet {
    protected static ProductDao productDataStore;
    protected static ProductCategoryDao productCategoryDataStore;
    protected static SupplierDao supplierDataStore;
    protected static OrderDao orderDataStore;
    protected static PaymentDao paymentDataStore;

}
