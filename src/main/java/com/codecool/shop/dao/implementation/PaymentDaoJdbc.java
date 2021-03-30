package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.PaymentDao;
import com.codecool.shop.model.Payment;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class PaymentDaoJdbc implements PaymentDao {

    private DataSource dataSource;

    public PaymentDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Payment payment) {
        try (Connection connection = dataSource.getConnection()){
            String sql = "INSERT INTO payment (card_type, card_number, expire_year, expire_month, cvc, paypal_user, paypal_password) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,payment.getCardType());
            statement.setString(2,payment.getCardNumber());
            statement.setInt(3,payment.getExpYear());
            statement.setInt(4,payment.getExpMonth());
            statement.setInt(5,payment.getCreditCardCode());
            statement.setString(6,payment.getPayPalUserName());
            statement.setString(7,payment.getPayPalPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            payment.setId(resultSet.getInt(1));
        } catch (SQLException e){throw new RuntimeException(e);}
    }

    @Override
    public Payment find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Payment> getAll() {
        return null;
    }
}
