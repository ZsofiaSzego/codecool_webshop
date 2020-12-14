package com.codecool.shop.model;

public class LineItem {
    private Product product;
    private int quantity;
    private float unitPrice;
    private float totalPrice;
    int id;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        setUnitPrice();
        setTotalPrice();
        setId();
    }

    public void increaseQuantity(){
        this.quantity = this.quantity + 1;
    }
    public void decreaseQuantity(){
        this.quantity = this.quantity - 1;
    }

    public String getItemName(){
        return this.getProduct().getName();
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = product.getId();
    }

    public Product getProduct() {
        return product;
    }

    public boolean isSameProduct(Product product){
        return product.getId() == this.id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice() {
        this.unitPrice = product.getDefaultPrice();
    }

    public double getTotalPrice() {
        this.setTotalPrice();
        return (double)Math.round(totalPrice*100)/100;
    }

    public void setTotalPrice() {
        this.totalPrice = unitPrice * quantity;
    }
}
