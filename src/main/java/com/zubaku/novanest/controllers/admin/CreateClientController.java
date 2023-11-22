package com.zubaku.novanest.controllers.admin;

import static com.zubaku.novanest.utils.constants.Constants.TRANSACTION_LIMIT_PER_DAY;
import static com.zubaku.novanest.utils.constants.Constants.WITHDRAWAL_LIMIT;

import com.zubaku.novanest.models.Model;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import com.zubaku.novanest.utils.enums.AccountType;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateClientController implements Initializable {
  public TextField firstNameTextField;
  public TextField lastNameTextField;
  public TextField passwordTextField;
  public CheckBox payeeAddressChoiceBox;
  public Label payeeAddressLabel;
  public CheckBox addCheckingAccountCheckBox;
  public TextField checkingAccountBalanceAmountTextField;
  public CheckBox addSavingsAccountCheckBox;
  public TextField savingsAccountBalanceAmountTextField;
  public Button createNewClientButton;
  public Label errorLabel;

  private String payeeAddress;
  private boolean checkingAccountCreated = false;
  private boolean savingsAccountCreated = false;

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  private void createClient() {
    // Create checking account
    if (checkingAccountCreated) {
      createAccount(AccountType.CHECKING);
    }

    // Create savings account
    if (savingsAccountCreated) {
      createAccount(AccountType.SAVINGS);
    }

    // Create client
    Model.getInstance()
        .getRepository()
        .createClient(
            firstNameTextField.getText(),
            lastNameTextField.getText(),
            payeeAddress,
            passwordTextField.getText(),
            LocalDate.now());
    errorLabel.setStyle("-fx-text-fill: green; -fx-font-size: 1.3em; -fx-font-weight: bold");
    errorLabel.setText("Client created successfully!");

    // Clear fields
    clearFields();
  }

  private void clearFields() {
    firstNameTextField.clear();
    lastNameTextField.clear();
    passwordTextField.clear();
    payeeAddressChoiceBox.setSelected(false);
    payeeAddressLabel.setText(null);
    addCheckingAccountCheckBox.setSelected(false);
    checkingAccountBalanceAmountTextField.clear();
    addSavingsAccountCheckBox.setSelected(false);
    savingsAccountBalanceAmountTextField.clear();
    createNewClientButton.setDisable(true);
    errorLabel.setText(null);
  }

  private void createAccount(AccountType accountType) {
    double balance = Double.parseDouble(checkingAccountBalanceAmountTextField.getText());
    // Generate account number
    String firstPart = "3201";
    String lastPart = Integer.toString(new Random().nextInt(999) + 1000);
    String accountNumber = firstPart + " " + lastPart;

    // Create the checking or savings account
    if (accountType == AccountType.CHECKING) {
      Model.getInstance()
          .getRepository()
          .createCheckingAccount(payeeAddress, accountNumber, TRANSACTION_LIMIT_PER_DAY, balance);
    } else {
      Model.getInstance()
          .getRepository()
          .createSavingsAccount(payeeAddress, accountNumber, WITHDRAWAL_LIMIT, balance);
    }
  }
}
