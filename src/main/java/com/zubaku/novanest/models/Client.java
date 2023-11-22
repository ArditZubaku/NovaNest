package com.zubaku.novanest.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Client {
  private final StringProperty firstName;
  private final StringProperty lastName;
  private final StringProperty payeeAddress;
  private final ObjectProperty<Account> checkingAccount;
  private final ObjectProperty<Account> savingsAccount;
  private final ObjectProperty<LocalDate> createdDate;

  public Client(
      String firstName,
      String lastName,
      String payeeAddress,
      Account checkingAccount,
      Account savingsAccount,
      LocalDate date) {
    this.firstName = new SimpleStringProperty(this, "FirstName", firstName);
    this.lastName = new SimpleStringProperty(this, "LastName", lastName);
    this.payeeAddress = new SimpleStringProperty(this, "PayeeAddress", payeeAddress);
    this.checkingAccount = new SimpleObjectProperty<>(this, "CheckingAccount", checkingAccount);
    this.savingsAccount = new SimpleObjectProperty<>(this, "SavingsAccount", savingsAccount);
    this.createdDate = new SimpleObjectProperty<>(this, "CreatedDate", date);
  }

  public StringProperty firstNameProperty() {
    return firstName;
  }

  public StringProperty lastNameProperty() {
    return lastName;
  }

  public StringProperty payeeAddressProperty() {
    return payeeAddress;
  }

  public ObjectProperty<Account> checkingAccountProperty() {
    return checkingAccount;
  }

  public ObjectProperty<Account> savingsAccountProperty() {
    return savingsAccount;
  }

  public ObjectProperty<LocalDate> createdDateProperty() {
    return createdDate;
  }
}
