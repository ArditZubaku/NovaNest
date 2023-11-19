package com.zubaku.novanest.controllers;

import com.zubaku.novanest.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
  public ChoiceBox accountSelector;
  public Label payeeAddressLabel;
  public TextField payeeAddressField;
  public Label passwordLabel;
  public PasswordField passwordField;
  public Button loginButton;
  public Label errorLabel;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    loginButton.setOnAction(event -> Model.getInstance().getViewProcessor().showClientWindow());
  }
}
