package com.zubaku.novanest.models;

import com.zubaku.novanest.processors.ViewProcessor;
import com.zubaku.novanest.repository.Repository;
import com.zubaku.novanest.utils.enums.AccountType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Model {
  private final ViewProcessor viewProcessor;
  private final Repository repository;
  // Client data section
  private final Client client;
  private boolean clientLoggedInSuccessfully;

  // Admin data section
  private boolean adminLoggedInSuccessfully;

  // Private constructor.
  private Model() {
    this.viewProcessor = new ViewProcessor();
    this.repository = new Repository();

    // Client data section
    this.clientLoggedInSuccessfully = false;
    this.client = new Client("", "", "", null, null, null);

    // Admin data section
    this.adminLoggedInSuccessfully = false;
  }

  // Static inner class for holding the instance.
  private static class Holder {
    private static final Model INSTANCE = new Model();
  }

  // Public method to get the instance.
  public static Model getInstance() {
    return Holder.INSTANCE;
  }

  public void evaluateClientCredentials(String payeeAddress, String password) {
    CheckingAccount checkingAccount;
    SavingsAccount savingsAccount;

    ResultSet resultSet = repository.getClientData(payeeAddress, password);

    try {
      if (resultSet.isBeforeFirst()) {
        while (resultSet.next()) {
          // getString -> name of the column
          this.client.firstNameProperty().set(resultSet.getString("FirstName"));
          this.client.lastNameProperty().set(resultSet.getString("LastName"));
          this.client.payeeAddressProperty().set(resultSet.getString("PayeeAddress"));
          String[] dateArray = resultSet.getString("Date").split("-");
          LocalDate date =
              LocalDate.of(
                  Integer.parseInt(dateArray[0]),
                  Integer.parseInt(dateArray[1]),
                  Integer.parseInt(dateArray[2]));
          this.client.createdDateProperty().set(date);
          this.clientLoggedInSuccessfully = true;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void evaluateAdminCredentials(String username, String password) {
    ResultSet resultSet = repository.getAdminData(username, password);

    try {
      if (resultSet.isBeforeFirst()) {
        while (resultSet.next()) {
          this.adminLoggedInSuccessfully = true;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public ViewProcessor getViewProcessor() {
    return viewProcessor;
  }

  public Repository getRepository() {
    return repository;
  }

  public Client getClient() {
    return client;
  }

  public boolean isClientLoggedInSuccessfully() {
    return clientLoggedInSuccessfully;
  }

  public void setClientLoggedInSuccessfully(boolean clientLoggedInSuccessfully) {
    this.clientLoggedInSuccessfully = clientLoggedInSuccessfully;
  }

  public boolean isAdminLoggedInSuccessfully() {
    return adminLoggedInSuccessfully;
  }

  public void setAdminLoggedInSuccessfully(boolean adminLoggedInSuccessfully) {
    this.adminLoggedInSuccessfully = adminLoggedInSuccessfully;
  }
}
