package com.zubaku.novanest.controllers.client;

import com.zubaku.novanest.models.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
  public Label dateLabel;
  public Button deleteButton;
  public Label savingsAccountLabel;
  public Label checkingAccountLabel;
  public Label pAddressLabel;
  public Label lastNameLabel;
  public Label firstNameLabel;

  private final Client client;

  public ClientCellController(Client client) {
    this.client = client;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {}
}
