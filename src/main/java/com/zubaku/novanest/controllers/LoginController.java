package com.zubaku.novanest.controllers;

import com.zubaku.novanest.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    loginButton.setOnAction(event -> onLogin());
  }

  private void onLogin() {
    Stage stage = (Stage) errorLabel.getScene().getWindow();
    Model.getInstance().getViewProcessor().closeStage(stage);
    Model.getInstance().getViewProcessor().showClientWindow();
  }
}
