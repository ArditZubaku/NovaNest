package com.zubaku.novanest.repository;

import com.zubaku.novanest.processors.ViewProcessor;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseDriver {
  private static final Logger LOGGER = Logger.getLogger(ViewProcessor.class.getName());

  private Connection connection;

  public DatabaseDriver() {
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
      LOGGER.log(Level.SEVERE, "Error getting client data", e);
    }
    return resultSet;
  }

  /*
   * Admin Section
   */
}
