package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.PaymentDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentDaoMem implements PaymentDao {
    private List<Payment> data = new ArrayList<>();
    private static PaymentDaoMem instance = null;

    private PaymentDaoMem(){    }

    public static PaymentDaoMem getInstance(){
        if (instance == null){
            instance = new PaymentDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Payment payment) {
        payment.setId(data.size()+1);
        data.add(payment);
    }

    @Override
    public Payment find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Payment> getAll() {
        return data;    }


}
