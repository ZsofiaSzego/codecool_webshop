package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.model.Address;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class AddressDaoJdbc implements AddressDao {

    private DataSource dataSource;

    public AddressDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Address address) {
        try (Connection connection = dataSource.getConnection()){
            String sql = "INSERT INTO address(country, city, zipcode, address)"+
                    "VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,address.getCountry());
            statement.setString(2, address.getCity());
            statement.setInt(3,address.getZipCode());
            statement.setString(4,address.getAddress());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            address.setId(resultSet.getInt(1));
        } catch (SQLException e){throw new RuntimeException(e);}
    }

    public boolean isAlreadyInDb(Address address){
        try (Connection connection = dataSource.getConnection()){
            String sql = "SELECT country, city, zipcode, address FROM address " +
                    "WHERE country = ? AND city = ? AND zipcode = ? AND address = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setInt(3, address.getZipCode());
            statement.setString(4, address.getAddress());
            ResultSet resultSet = statement.executeQuery();
            if (! resultSet.next() ){ return false; }
            else{ return true; }
        } catch (SQLException e){throw new RuntimeException(e);}
    }

    @Override
    public int find(Address address) {
        try (Connection connection = dataSource.getConnection()){
            String sql = "SELECT id, country, city, zipcode, address FROM address " +
                    "WHERE country = ? AND city = ? AND zipcode = ? AND address = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setInt(3, address.getZipCode());
            statement.setString(4, address.getAddress());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e){throw new RuntimeException(e);}
    }

    @Override
    public Address find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Address> getAll() {
        return null;
    }
}
