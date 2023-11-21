package com.zubaku.novanest.controllers.admin;

import com.zubaku.novanest.models.Model;
import com.zubaku.novanest.utils.enums.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
  public Button createClientButton;
  public Button clientsButton;
  public Button depositButton;
  public Button logoutButton;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    addListeners();
  }

  private void addListeners() {
    createClientButton.setOnAction(event -> onCreateClient());
    clientsButton.setOnAction(event -> onClients());
    depositButton.setOnAction(event -> onDeposit());
  }

  private void onCreateClient() {
    Model.getInstance()
        .getViewProcessor()
        .getAdminSelectedMenuItem()
        .set(AdminMenuOptions.CREATE_CLIENT);
  }

  private void onClients() {
    Model.getInstance().getViewProcessor().getAdminSelectedMenuItem().set(AdminMenuOptions.CLIENTS);
  }

  private void onDeposit() {
    Model.getInstance().getViewProcessor().getAdminSelectedMenuItem().set(AdminMenuOptions.DEPOSIT);
  }
}
