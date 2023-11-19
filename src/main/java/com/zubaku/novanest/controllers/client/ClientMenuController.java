package com.zubaku.novanest.controllers.client;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
  public Button dashboardButton;
  public Button transactionButton;
  public Button accountsButton;
  public Button profileButton;
  public Button logoutButton;
  public Button reportButton;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    dashboardButton.setOnAction(event -> System.out.println("Dashboard button invoked"));
  }
}
