package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private static Cart cart;
    private DataSource dataSource;

    public ProductDaoJdbc(DataSource dataSource){this.dataSource = dataSource;}

    @Override
    public void add(Product product) {
        try (Connection connection = dataSource.getConnection()){
            String sql = "INSERT INTO products (name, description, default_price, currency, supplier, product_category) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getDefaultPrice());
            statement.setString(4, product.getDefaultCurrency().toString());
            statement.setInt(5, product.getSupplier().getId());
            statement.setInt(6, product.getProductCategory().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getInt(1));
        } catch (SQLException e){throw new RuntimeException(e);}
    }

    @Override
    public Product find(int id) {
        try (Connection connection = dataSource.getConnection()){
            String sql = "SELECT id, name, description, default_price, currency, supplier, product_category FROM products WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (! resultSet.next() ){return null;}
            ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc(dataSource);
            SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc(dataSource);
            Product product = new Product(resultSet.getString(2), resultSet.getFloat(4), resultSet.getString(5), resultSet.getString(3),
                    productCategoryDaoJdbc.find(resultSet.getInt(6)), supplierDaoJdbc.find(resultSet.getInt(7)));
            product.setId(resultSet.getInt(1));
            return product;
        } catch (SQLException e){throw new RuntimeException(e);}
    }

    @Override
    public void remove(int id) {
        try (Connection connection = dataSource.getConnection()){
            String sql ="DELETE FROM products WHERE id= ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
        } catch (SQLException e){throw new RuntimeException(e);}
    }

    public List<Product> getProductList(ResultSet resultSet) throws SQLException {
        List<Product> resultList = new ArrayList<>();
        while (resultSet.next()){
            ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc(dataSource);
            SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc(dataSource);
            Product product = new Product(resultSet.getString(2), resultSet.getFloat(3),
                    resultSet.getString(4), resultSet.getString(5),
                    productCategoryDaoJdbc.find(resultSet.getInt(6)),
                    supplierDaoJdbc.find(resultSet.getInt(7)));
            product.setId(resultSet.getInt(1));
            resultList.add(product);
        }
        return resultList;
    }

    @Override
    public List<Product> getAll() {
        try (Connection connection = dataSource.getConnection()){
            String sql = "SELECT id, name, default_price, currency, description, product_category, supplier FROM products";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            return getProductList(resultSet);
        } catch (SQLException e){throw new RuntimeException(e);}
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        try (Connection connection = dataSource.getConnection()){
            String sql = "SELECT id, name, default_price, currency, description, product_category, supplier " +
                    "FROM products WHERE supplier=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, supplier.getId());
            ResultSet resultSet = statement.executeQuery();
            return getProductList(resultSet);
        } catch (SQLException e){throw new RuntimeException(e);}
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        try (Connection connection = dataSource.getConnection()){
            String sql = "SELECT id, name, default_price, currency, description, product_category, supplier " +
                    "FROM products WHERE product_category=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productCategory.getId());
            ResultSet resultSet = statement.executeQuery();
            return getProductList(resultSet);
        } catch (SQLException e){throw new RuntimeException(e);}
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory, Supplier supplier) {
        try (Connection connection = dataSource.getConnection()){
            String sql = "SELECT id, name, default_price, currency, description, product_category, supplier " +
                    "FROM products WHERE product_category=? AND supplier=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productCategory.getId());
            statement.setInt(2, supplier.getId());
            ResultSet resultSet = statement.executeQuery();
            return getProductList(resultSet);
        } catch (SQLException e){throw new RuntimeException(e);}
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
