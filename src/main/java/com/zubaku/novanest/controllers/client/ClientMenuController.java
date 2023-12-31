package com.zubaku.novanest.controllers.client;

import com.zubaku.novanest.models.Model;
import java.net.URL;
import java.util.ResourceBundle;

import com.zubaku.novanest.utils.enums.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
    logoutButton.setOnAction(event -> onLogout());
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

  private void onLogout() {
    Stage stage = (Stage) logoutButton.getScene().getWindow();
    Model.getInstance().getViewProcessor().closeStage(stage);
    Model.getInstance().getViewProcessor().showLoginWindow();
    Model.getInstance().setClientLoggedInSuccessfully(false);
  }
}
