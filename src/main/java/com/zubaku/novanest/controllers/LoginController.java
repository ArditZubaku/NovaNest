package com.zubaku.novanest.controllers;

import com.zubaku.novanest.models.Model;
import com.zubaku.novanest.utils.enums.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
  public ChoiceBox<AccountType> accountSelector;
  public Label payeeAddressLabel;
  public TextField payeeAddressField;
  public Label passwordLabel;
  public PasswordField passwordField;
  public Button loginButton;
  public Label errorLabel;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // Set available options
    accountSelector.setItems(
        FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
    // Default value
    accountSelector.setValue(Model.getInstance().getViewProcessor().getLogInAccountType());
    // Update the value every time we change the selection
    accountSelector.valueProperty().addListener(observable -> setAccountSelector());
    loginButton.setOnAction(event -> onLogin());
  }

  private void onLogin() {
    Stage stage = (Stage) errorLabel.getScene().getWindow();
    // Show the window based on the selected AccountType
    if (Model.getInstance().getViewProcessor().getLogInAccountType() == AccountType.CLIENT) {
      Model.getInstance()
          .evaluateClientCredentials(payeeAddressField.getText(), passwordField.getText());
      if (Model.getInstance().isClientLoggedInSuccessfully()) {
        // Close the login stage
        Model.getInstance().getViewProcessor().closeStage(stage);
        Model.getInstance().getViewProcessor().showClientWindow();
      } else {
        payeeAddressField.setText(null);
        passwordField.setText(null);
        errorLabel.setText("Wrong credentials! Please try again.");
      }
    } else {
      Model.getInstance()
          .evaluateAdminCredentials(payeeAddressField.getText(), passwordField.getText());
      if (Model.getInstance().isAdminLoggedInSuccessfully()) {
        // Close the login stage
        Model.getInstance().getViewProcessor().closeStage(stage);
        Model.getInstance().getViewProcessor().showAdminWindow();
      } else {
        payeeAddressField.setText(null);
        passwordField.setText(null);
        errorLabel.setText("Wrong credentials! Please try again.");
      }
    }
  }

  private void setAccountSelector() {
    Model.getInstance().getViewProcessor().setLogInAccountType(accountSelector.getValue());
    if (accountSelector.getValue() == AccountType.ADMIN) {
      payeeAddressLabel.setText("Username:");
    } else {
      payeeAddressLabel.setText("Payee Address:");
    }
  }
}
