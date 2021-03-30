package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class OrderDaoJdbc implements OrderDao {
    private DataSource dataSource;

    public OrderDaoJdbc(DataSource dataSource){this.dataSource = dataSource;}

    @Override
    public void add(Order order) {
        try (Connection connection = dataSource.getConnection()){
            String sql = "INSERT INTO orders (name, email, phone_number, shipping_address, billing_address) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, order.getName());
            statement.setString(2, order.getEmail());
            statement.setString(3, order.getPhoneNumber());
            statement.setInt(4, order.getShippingAddress().getId());
            statement.setInt(5, order.getBillingAddress().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            order.setId(resultSet.getInt(1));
        } catch (SQLException e){throw new RuntimeException(e);}
    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
