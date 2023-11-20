package com.zubaku.novanest.controllers.admin;

import com.zubaku.novanest.models.Model;
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
  }

  private void onCreateClient() {
    Model.getInstance().getViewProcessor().getAdminSelectedMenuItem().set("Create Client ");
  }
}
