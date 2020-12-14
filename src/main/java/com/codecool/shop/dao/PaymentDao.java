package com.codecool.shop.dao;

import com.codecool.shop.model.Payment;

import java.util.List;

public interface PaymentDao {
    void add(Payment payment);
    Payment find(int id);
    void remove(int id);

    List<Payment> getAll();
}
