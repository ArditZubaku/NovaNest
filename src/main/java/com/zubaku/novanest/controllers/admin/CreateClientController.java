package com.zubaku.novanest.controllers.admin;

import static com.zubaku.novanest.utils.constants.Constants.TRANSACTION_LIMIT_PER_DAY;
import static com.zubaku.novanest.utils.constants.Constants.WITHDRAWAL_LIMIT;

import com.zubaku.novanest.models.Model;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
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

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  private void createAccount(String accountType) {
    double balance = Double.parseDouble(checkingAccountBalanceAmountTextField.getText());
    // Generate account number
    String firstPart = "3201";
    String lastPart = Integer.toString(new Random().nextInt(999) + 1000);
    String accountNumber = firstPart + " " + lastPart;

    // Create the checking or savings account
    if (accountType.equals("Checking")) {
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
