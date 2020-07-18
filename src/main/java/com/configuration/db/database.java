package com.configuration.db;

import com.configuration.domain.Customer;
import jdk.nashorn.internal.runtime.ECMAException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class database {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "rootroot";

    private static final String INSERTSQL = "Insert into business.product"
            + " (product_name, price,shop_id)values " + " (?,?,?); ";

    private static final String SELECTBYID = "Select product_id, product_name, " +
            "price,shop_id from business.product where product_id=?";

    private static final String SELECTALL = "Select * from business.product";

    private static final String DELETESQL = "Delete from business.product where product_id=?";

    private static final String UPDATEDQL = "Update business.product set product_name=?," +
            " price=?, shop_id=? where product_id=? ;";


    static Driver driver;

    static {
        try {
            driver = new com.mysql.cj.jdbc.Driver();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public database() throws SQLException {
    }


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
           // DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return connection;
    }

    //Create or insert product_name;

    public void insertSql(Customer customer) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERTSQL)) {
            preparedStatement.setString(1, customer.getProductName());
            preparedStatement.setString(2, customer.getPrice());
            preparedStatement.setString(3, customer.getShopId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    // update product_name;

    public boolean updateSql(Customer customer) throws SQLException {
        boolean rowUpdate;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATEDQL);) {
            statement.setString(2, customer.getProductName());
            statement.setString(3, customer.getPrice());
            statement.setString(4, customer.getShopId());
            statement.setLong(1, customer.getId());

            rowUpdate = statement.executeUpdate() > 0;
        }
        return rowUpdate;
    }
    //select business_product by id

    public Customer selectUser(Long id) throws SQLException {
        Customer customer = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECTBYID)) {
            preparedStatement.setLong(1, customer.getId());
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String product = rs.getString("product_name");
                String price = rs.getString("price");
                String shop = rs.getString("shop_id");
                customer = new Customer(id, product, price, shop);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return customer;
    }

    //select all product
    public List<Customer> selectAllUser() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECTBYID)) {

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("product_id");
                String product = rs.getString("product_name");
                String price = rs.getString("price");
                String shop = rs.getString("shop_id");
                customers.add(new Customer(id, product, price, shop));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return customers;
    }

    //delete products;
    public boolean deleteSql(Long Id) throws SQLException {
        boolean rowDelete;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETESQL)) {
            preparedStatement.setLong(1, Id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        }
        return rowDelete;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
