package com.codecool.shop.model;

public class Address {
    int id;
    String country;
    String city;
    int zipCode;
    String address;

    public Address(String country, String city, String zipCode, String address) {
        setCountry(country);
        setCity(city);
        setZipCode(zipCode);
        setAddress(address);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if(country.matches("[a-zA-Z]+")){
        this.country = country;}
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if(city.matches("[a-zA-Z]+")){
            this.city = city;}
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        try{this.zipCode = Integer.parseInt(zipCode);}
        catch(NumberFormatException e){
            this.zipCode = 0;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean allFieldsFilled(){
        return country != null && city != null && zipCode != 0 && address != null;
    }

}
