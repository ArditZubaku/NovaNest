package com.zubaku.novanest.repository;

import com.zubaku.novanest.processors.ViewProcessor;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Repository {
  private static final Logger LOGGER = Logger.getLogger(ViewProcessor.class.getName());

  private Connection connection;

  public Repository() {
    try {
      this.connection = DriverManager.getConnection("jdbc:sqlite:novanest.db");
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, "Error connecting to the database", e);
    }
  }

  /*
   * Client Section
   */

  public ResultSet getClientData(String payeeAddress, String password) {
    Statement statement;
    ResultSet resultSet = null;
    try {
      statement = this.connection.createStatement();
      resultSet =
          statement.executeQuery(
              "SELECT * FROM Clients WHERE PayeeAddress = '"
                  + payeeAddress
                  + "' AND Password = '"
                  + password
                  + "'");
    } catch (SQLException e) {
    }
    return resultSet;
  }

  /*
   * Admin Section
   */

  public ResultSet getAdminData(String username, String password) {
    Statement statement;
    ResultSet resultSet = null;

    try {
      statement = this.connection.createStatement();
      resultSet =
          statement.executeQuery(
              "SELECT * FROM Admins WHERE Username = '"
                  + username
                  + "' AND Password = '"
                  + password
                  + "'");
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, "Error getting admin data", e);
    }
    return resultSet;
  }

  public void createClient(
      String firstName, String lastName, String payeeAddress, String password, LocalDate date) {
    Statement statement;
    try {
      statement = this.connection.createStatement();
      //      statement.executeUpdate("CREATE TABLE IF NOT EXISTS Clients");
      statement.executeUpdate(
          "INSERT INTO Clients(FirstName, LastName, PayeeAddress, Password, Date)"
              + "VALUES ('"
              + firstName
              + "', '"
              + lastName
              + "','"
              + payeeAddress
              + "','"
              + password
              + "','"
              + date.toString()
              + "')");
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, "Error creating client", e);
    }
  }
}
