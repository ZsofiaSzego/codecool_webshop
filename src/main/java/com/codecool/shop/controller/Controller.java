package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;

import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

public class Controller extends HttpServlet {

    protected static ProductDao productDataStore;
    protected static ProductCategoryDao productCategoryDataStore;
    protected static SupplierDao supplierDataStore;
    protected static OrderDao orderDataStore;
    protected static PaymentDao paymentDataStore;
    protected static AddressDao addressDataStore;

    public Controller() {
    }

    public static ProductDao getProductDataStore() {
        return productDataStore;
    }

    public static void setProductDataStore(ProductDao productDataStore) {
        Controller.productDataStore = productDataStore;
    }

    public static ProductCategoryDao getProductCategoryDataStore() {
        return productCategoryDataStore;
    }

    public static void setProductCategoryDataStore(ProductCategoryDao productCategoryDataStore) {
        Controller.productCategoryDataStore = productCategoryDataStore;
    }

    public static SupplierDao getSupplierDataStore() {
        return supplierDataStore;
    }

    public static void setSupplierDataStore(SupplierDao supplierDataStore) {
        Controller.supplierDataStore = supplierDataStore;
    }

    public static OrderDao getOrderDataStore() {
        return orderDataStore;
    }

    public static void setOrderDataStore(OrderDao orderDataStore) {
        Controller.orderDataStore = orderDataStore;
    }

    public static PaymentDao getPaymentDataStore() {
        return paymentDataStore;
    }

    public static void setPaymentDataStore(PaymentDao paymentDataStore) {
        Controller.paymentDataStore = paymentDataStore;
    }

    public static AddressDao getAddressDataStore() {
        return addressDataStore;
    }

    public static void setAddressDataStore(AddressDao addressDataStore) {
        Controller.addressDataStore = addressDataStore;
    }
}
