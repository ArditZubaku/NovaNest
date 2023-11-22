package com.zubaku.novanest.repository;

import com.zubaku.novanest.processors.ViewProcessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseDriver {
  private static final Logger LOGGER = Logger.getLogger(ViewProcessor.class.getName());

  private Connection connection;

  public DatabaseDriver(Connection connection) {
    try {
      this.connection = DriverManager.getConnection("jdbc:sqlite:novanest.db");
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, "Error connecting to the database", e);
    }
  }

  /*
  * Client Section
  */

  /*
  * Admin Section
  */
}
