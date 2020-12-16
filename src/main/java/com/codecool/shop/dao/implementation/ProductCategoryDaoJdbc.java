package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {
    private DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource){this.dataSource = dataSource;}

    @Override
    public void add(ProductCategory category) {
        try (Connection connection = dataSource.getConnection()){
            String sql = "INSERT INTO product_category (name, description, department) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setString(3, category.getDepartment());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            category.setId(resultSet.getInt(1));
        } catch (SQLException e){throw new RuntimeException(e);}

    }

    @Override
    public ProductCategory find(int id) {
        try (Connection connection = dataSource.getConnection()){
            String sql = "SELECT id, name, description, department FROM product_category WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (! resultSet.next() ){return null;}
            ProductCategory productCategory = new ProductCategory(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            productCategory.setId(resultSet.getInt(1));
            return productCategory;
        } catch (SQLException e){throw new RuntimeException(e);}
    }

    @Override
    public void remove(int id) {
        try (Connection connection = dataSource.getConnection()){
            String sql ="DELETE FROM product_category WHERE id= ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
        } catch (SQLException e){throw new RuntimeException(e);}
    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection connection = dataSource.getConnection()){
            String sql = "SELECT id, name, description, department FROM product_category";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            List<ProductCategory> resultList = new ArrayList<>();

            while (resultSet.next()){
                ProductCategory productCategory = new ProductCategory(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                productCategory.setId(resultSet.getInt(1));
                resultList.add(productCategory);
            }
            return resultList;
        } catch (SQLException e){throw new RuntimeException(e);}    }
}
