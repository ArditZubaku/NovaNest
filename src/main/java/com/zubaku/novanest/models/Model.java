package com.zubaku.novanest.models;

import com.zubaku.novanest.processors.ViewProcessor;
import com.zubaku.novanest.repository.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
  private final ViewProcessor viewProcessor;
  private final Repository repository;
  // Client data section
  private final Client client;
  private boolean clientLoggedInSuccessfully;
  private final ObservableList<Transaction> latestTransactions;
  private final ObservableList<Transaction> allTransactions;

  // Admin data section
  private boolean adminLoggedInSuccessfully;
  private final ObservableList<Client> clients;

  // Private constructor.
  private Model() {
    this.viewProcessor = new ViewProcessor();
    this.repository = new Repository();

    // Client data section
    this.clientLoggedInSuccessfully = false;
    this.client = new Client("", "", "", null, null, null);
    this.latestTransactions = FXCollections.observableArrayList();
    this.allTransactions = FXCollections.observableArrayList();

    // Admin data section
    this.adminLoggedInSuccessfully = false;
    this.clients = FXCollections.observableArrayList();
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
          checkingAccount = getCheckingAccount(payeeAddress);
          savingsAccount = getSavingsAccount(payeeAddress);
          this.client.checkingAccountProperty().set(checkingAccount);
          this.client.savingsAccountProperty().set(savingsAccount);
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

  public CheckingAccount getCheckingAccount(String payeeAddress) {
    CheckingAccount checkingAccount = null;
    ResultSet resultSet = repository.getCheckingAccountData(payeeAddress);

    try {
      if (resultSet.isBeforeFirst()) {
        while (resultSet.next()) {
          String owner = resultSet.getString("Owner");
          String accountNumber = resultSet.getString("AccountNumber");
          int transactionLimit = resultSet.getInt("TransactionLimit");
          double balance = resultSet.getDouble("Balance");
          checkingAccount = new CheckingAccount(owner, accountNumber, balance, transactionLimit);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return checkingAccount;
  }

  public SavingsAccount getSavingsAccount(String payeeAddress) {
    SavingsAccount savingsAccount = null;
    ResultSet resultSet = repository.getSavingsAccountData(payeeAddress);

    try {
      if (resultSet.isBeforeFirst()) {
        while (resultSet.next()) {
          String owner = resultSet.getString("Owner");
          String accountNumber = resultSet.getString("AccountNumber");
          double withdrawalLimit = resultSet.getDouble("WithdrawalLimit");
          double balance = resultSet.getDouble("Balance");
          savingsAccount = new SavingsAccount(owner, accountNumber, balance, withdrawalLimit);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return savingsAccount;
  }

  public ObservableList<Client> getClients() {
    return clients;
  }

  public void setClients() {
    CheckingAccount checkingAccount;
    SavingsAccount savingsAccount;

    ResultSet allClientsData = repository.getAllClientsData();

    try {
      if (allClientsData.isBeforeFirst()) {
        while (allClientsData.next()) {
          String firstName = allClientsData.getString("FirstName");
          String lastName = allClientsData.getString("LastName");
          String payeeAddress = allClientsData.getString("PayeeAddress");
          String[] dateArray = allClientsData.getString("Date").split("-");
          LocalDate date =
              LocalDate.of(
                  Integer.parseInt(dateArray[0]),
                  Integer.parseInt(dateArray[1]),
                  Integer.parseInt(dateArray[2]));
          checkingAccount = getCheckingAccount(payeeAddress);
          savingsAccount = getSavingsAccount(payeeAddress);
          clients.add(
              new Client(firstName, lastName, payeeAddress, checkingAccount, savingsAccount, date));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public ObservableList<Client> searchClient(String payeeAddress) {
    ObservableList<Client> searchResults = FXCollections.observableArrayList();
    ResultSet resultSet = repository.searchClient(payeeAddress);
    try {
      if (resultSet.isBeforeFirst()) {
        while (resultSet.next()) {
          CheckingAccount checkingAccount = getCheckingAccount(payeeAddress);
          SavingsAccount savingsAccount = getSavingsAccount(payeeAddress);
          String firstName = resultSet.getString("FirstName");
          String lastName = resultSet.getString("LastName");
          String[] dateArray = resultSet.getString("Date").split("-");
          LocalDate date =
              LocalDate.of(
                  Integer.parseInt(dateArray[0]),
                  Integer.parseInt(dateArray[1]),
                  Integer.parseInt(dateArray[2]));
          searchResults.add(
              new Client(firstName, lastName, payeeAddress, checkingAccount, savingsAccount, date));
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return searchResults;
  }

  private void prepareTransactions(ObservableList<Transaction> transactions, int limit) {
    ResultSet resultSet =
        repository.getClientTransactions(this.client.payeeAddressProperty().get(), limit);

    try {
      if (resultSet.isBeforeFirst()) {
        while (resultSet.next()) {
          String sender = resultSet.getString("Sender");
          String receiver = resultSet.getString("Receiver");
          double amount = resultSet.getDouble("Amount");
          String[] dateArray = resultSet.getString("Date").split("-");
          LocalDate date =
              LocalDate.of(
                  Integer.parseInt(dateArray[0]),
                  Integer.parseInt(dateArray[1]),
                  Integer.parseInt(dateArray[2]));
          String message = resultSet.getString("Message");
          transactions.add(new Transaction(sender, receiver, amount, date, message));
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void setLatestTransactions() {
    prepareTransactions(this.latestTransactions, 4);
  }

  public ObservableList<Transaction> getLatestTransactions() {
    return latestTransactions;
  }

  public void setAllTransactions() {
    // -1 -> returns all of them
    prepareTransactions(this.allTransactions, -1);
  }

  public ObservableList<Transaction> getAllTransactions() {
    return allTransactions;
  }
}
