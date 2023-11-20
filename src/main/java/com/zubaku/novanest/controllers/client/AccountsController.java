package com.zubaku.novanest.controllers.client;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountsController implements Initializable {
  public Label checkingAccountNumber;
  public Label transactionLimit;
  public Label checkingAccountCreatedDate;
  public Label checkingAccountBalance;
  public Label savingsAccountNumber;
  public Label withdrawalLimit;
  public Label savingsAccountCreatedDate;
  public Label savingsAccountBalance;
  public TextField amountToSavingsAccount;
  public Button transferToSavingsAccountButton;
  public TextField amountToCheckingAccount;
  public Button transferToCheckingAccountButton;

  @Override
  public void initialize(URL location, ResourceBundle resources) {}
}
