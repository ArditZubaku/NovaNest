package com.zubaku.novanest.controllers.client;

import com.zubaku.novanest.models.Model;
import java.net.URL;
import java.util.ResourceBundle;

import com.zubaku.novanest.utils.enums.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ClientMenuController implements Initializable {
  public Button dashboardButton;
  public Button transactionButton;
  public Button accountsButton;
  public Button profileButton;
  public Button logoutButton;
  public Button reportButton;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    addListeners();
  }

  private void addListeners() {
    dashboardButton.setOnAction(event -> onDashboard());
    transactionButton.setOnAction(event -> onTransaction());
    accountsButton.setOnAction(event -> onAccounts());
  }

  private void onTransaction() {
    Model.getInstance()
        .getViewProcessor()
        .getClientSelectedMenuItem()
        .set(ClientMenuOptions.TRANSACTIONS);
  }

  private void onDashboard() {
    Model.getInstance()
        .getViewProcessor()
        .getClientSelectedMenuItem()
        .set(ClientMenuOptions.DASHBOARD);
  }

  private void onAccounts() {
    Model.getInstance()
        .getViewProcessor()
        .getClientSelectedMenuItem()
        .set(ClientMenuOptions.ACCOUNTS);
  }
}
