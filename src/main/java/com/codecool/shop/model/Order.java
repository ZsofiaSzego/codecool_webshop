package com.codecool.shop.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Order {

    int id;
    Cart cart;
    String name;
    String email;
    String phoneNumber;
    Address shippingAddress;
    Address billingAddress;
    String nameRegx = "^[\\p{L} .'-]+$";
    String emailRegx = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    String phoneRegx = "^[0-9]{11}$|^\\+(?:[0-9] ?\\-?){6,14}[0-9]$";


    public Order(Cart cart, String name, String email, String phoneNumber, Address shippingAddress, Address billingAddress) {
        this.cart = cart;
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (validate(name, nameRegx)) {
            this.name = name;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (validate(email, emailRegx)) {
            this.email = email;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (validate(phoneNumber, phoneRegx)) {
            this.phoneNumber = phoneNumber;
        }
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public boolean validate(String inputString, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputString);
        return matcher.find();
    }

    public boolean allFieldsValid(){
        if(name!=null && email!=null && phoneNumber!=null){
            return shippingAddress.allFieldsFilled() && billingAddress.allFieldsFilled();
        }
        return false;
    }
}
