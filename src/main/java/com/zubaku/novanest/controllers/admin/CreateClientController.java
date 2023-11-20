package com.zubaku.novanest.controllers.admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

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

  @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
