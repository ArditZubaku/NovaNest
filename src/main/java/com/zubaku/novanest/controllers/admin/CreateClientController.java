package com.zubaku.novanest.controllers.admin;

import static com.zubaku.novanest.utils.constants.Constants.TRANSACTION_LIMIT_PER_DAY;
import static com.zubaku.novanest.utils.constants.Constants.WITHDRAWAL_LIMIT;

import com.zubaku.novanest.models.Model;
import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

import com.zubaku.novanest.utils.enums.AccountType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

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
  public void initialize(URL location, ResourceBundle resources) {
    createNewClientButton.setOnAction(event -> createClient());

    payeeAddressChoiceBox
        .selectedProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if (newValue) {
                payeeAddress = generatePayeeAddress();
                onGeneratePayeeAddress();
              }
            });
    addCheckingAccountCheckBox
        .selectedProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if (newValue) {
                checkingAccountCreated = true;
              }
            });
    addSavingsAccountCheckBox
        .selectedProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if (newValue) {
                savingsAccountCreated = true;
              }
            });
  }

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

  private void onGeneratePayeeAddress() {
    if (firstNameTextField.getText() != null && lastNameTextField.getText() != null) {
      payeeAddressLabel.setText(payeeAddress);
    }
  }

  private String generatePayeeAddress() {
    char firstLetterOfTheName = firstNameTextField.getText().toLowerCase().charAt(0);
    String lastName = lastNameTextField.getText();
    int id = Model.getInstance().getRepository().getLastClientsID() + 1;
    return "@" + firstLetterOfTheName + lastName + id;
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
    // Schedule to clear the error label after 5 seconds
    Timeline timeline =
        new Timeline(new KeyFrame(Duration.seconds(3), event -> errorLabel.setText(null)));
    timeline.play();
  }
}
