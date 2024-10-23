package com.example.products;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProductDAO {
  // driver
  final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  final String JDBC_URL = "jdbc:mysql://localhost:3306/backend?serverTimezone=Asia/Seoul";

  // db connection
  Connection conn;

  // statement
  PreparedStatement pstmt;

  // sql
  public void open() {
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(JDBC_URL, "root", "1111");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      log.info("Connected to database...");
    }
  }

  public void close() {
    try {
      pstmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      log.info("Closed database connection");
    }
  }

  public List<Product> findAll() {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM products";
    try {
      pstmt = conn.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        Product product = new Product(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("price"),
            rs.getString("manufacturer"),
            rs.getDate("manufacturing_date")
        );
        products.add(product);
      }
      return products;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Product findById(int id) {
    Product product = null;
    String sql = "SELECT * FROM products WHERE id =?";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        product = new Product(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("price"),
            rs.getString("manufacturer"),
            rs.getDate("manufacturing_date")
        );
      }
      return product;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void addProduct(Product product) {
    String sql = "INSERT INTO products(name, price, manufacturer, manufacturing_date) VALUES(?,?,?,?)";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, product.getName());
      pstmt.setInt(2, product.getPrice());
      pstmt.setString(3, product.getManufacturer());
      pstmt.setDate(4, (Date) product.getManufacturingDate());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void updateProduct(Product product) {
    // price, manufacturer, manufacturing_date만 수정
    String sql = "UPDATE products SET price = ?, manufacturer = ?, manufacturing_date = ? WHERE id = ?";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, product.getPrice());
      pstmt.setString(2, product.getManufacturer());
      pstmt.setDate(3, (Date) product.getManufacturingDate());
      pstmt.setInt(4, product.getId());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteProductById(int id) {
    String sql = "DELETE FROM products WHERE id = ?";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
