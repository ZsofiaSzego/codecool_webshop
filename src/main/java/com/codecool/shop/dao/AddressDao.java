package com.codecool.shop.dao;

import com.codecool.shop.model.Address;

import java.util.List;

public interface AddressDao {
    void add(Address address);
    Address find(int id);
    void remove(int id);
    boolean isAlreadyInDb(Address address);

    List<Address> getAll();

}
