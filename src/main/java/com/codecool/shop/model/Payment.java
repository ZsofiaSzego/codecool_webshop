package com.codecool.shop.model;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Payment {
    int id;
    String cardType;
    String cardNumber;
    String cardHolder;
    int expMonth;
    int expYear;
    int creditCardCode;
    String payPalUserName;
    String payPalPassword;
    String cardNumRegx = "^[0-9]{16}$";
    String cardHolderRegx = "^[a-zA-Z ]*$";
    String CVCRegx ="^[0-9]{3}$";
    String payPalUserNameRegx= "^[\\p{L} .'-]+$";


    public Payment(String cardType, String cardNumber, String cardHolder, String expMonth, String expYear) {
        setCardType(cardType);
        setCardNumber(cardNumber);
        setCardHolder(cardHolder);
        setExpMonth(expMonth);
        setExpYear(expYear);
    }

    public boolean validate(String inputString, String regex){
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputString);
        return matcher.find();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        if (validate(cardNumber, cardNumRegx)){
                this.cardNumber = cardNumber;
        }
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        if (validate(cardHolder, cardHolderRegx)){
            this.cardHolder = cardHolder;
        }
    }

    public int expYearToYear(int expYear){
        //since expYear only the last 2 digits of the year
        return 2000 + expYear;
    }

    public int getCurrentMontNum(){
        int month = Calendar.getInstance().get(Calendar.MONTH);
        // zero based so actual month is month+1
        return month+1;
    }

    public int getCurrentYearNum(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return 0;
    }

    public boolean validateExpDate(int expMonth, int expYear){
        if (expMonth >=1 && expMonth <= 12) {
            if (expYearToYear(expYear) > getCurrentYearNum()) {
                return true;
            }
            if (expYearToYear(expYear) == getCurrentYearNum()) {
                return expMonth >= getCurrentMontNum();
            } else {
                return false;
            }
        } else {return false;}
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        try {
            this.expMonth = Integer.parseInt(expMonth);
        } catch (NumberFormatException e){this.expMonth=0;}
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        try {
            this.expYear = Integer.parseInt(expYear);
        } catch (NumberFormatException e){this.expYear=0;}
    }

    public int getCreditCardCode() {
        return creditCardCode;
    }

    public void setCreditCardCode(String creditCardCode) {
        if (validate(creditCardCode, CVCRegx)){
            this.creditCardCode = Integer.parseInt(creditCardCode);
        }
    }

    public String getPayPalUserName() {
        return payPalUserName;
    }

    public void setPayPalUserName(String payPalUserName) {
        if (validate(payPalUserName, payPalUserNameRegx)){
        this.payPalUserName = payPalUserName;
        }
    }

    public String getPayPalPassword() {
        return payPalPassword;
    }

    public void setPayPalPassword(String payPalPassword) {
        if (payPalPassword.length() >= 8){
        this.payPalPassword = payPalPassword;
        }
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getId() {
        return id;
    }

    public boolean allFieldsValid(){
        if (cardType!=null && cardHolder!=null && cardNumber!=null && expMonth!= 0 && expYear!= 0){
            if (validateExpDate(expMonth, expYear)){
                if (cardType.equals("credit")){
                    return creditCardCode != 0;
                }else {
                    return payPalUserName != null && payPalPassword != null;
                }
            }
        }
        return false;
    }
}
