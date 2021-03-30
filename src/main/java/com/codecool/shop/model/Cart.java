package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    List<LineItem> productList;

    public Cart(){
        productList = new ArrayList<>();
    }

    public List<LineItem> getProductList() {
        return productList;
    }

    public void setProductList(List<LineItem> productList) {
        this.productList = productList;
    }

    public void delete(LineItem item){
        this.productList.remove(item);
    }

    public void remove(LineItem item){
            item.decreaseQuantity();
            if (item.getQuantity() <1){
                delete(item);
            }
    }
    public void addItem(LineItem item){
            item.increaseQuantity();
    }

    public void add(Product product){
        if (productList.isEmpty()){
            productList.add(new LineItem(product, 1));
        }
        else if (isProdAlreadyInList(product)){
            getItemById(product.getId()).increaseQuantity();
        } else {
            productList.add(new LineItem(product, 1));
        }
    }

    public LineItem getItemById(int id){
        for (LineItem l : productList) {
            if (l.getId() == id){
                return l;
            }
        }
        return null;
    }

    public boolean isProdAlreadyInList(Product product){
        for (LineItem l : productList) {
            if (l.getId() == product.getId()){
                return true;
            }
        } return false;
    }

    public boolean isAlredayInList(LineItem item){
        return getItemById(item.getId()) != null;
    }


    public int getSize(){
            int size = 0;
        for (LineItem i: productList) {
            size = size + i.getQuantity();
        }
        return size;
    }
}
