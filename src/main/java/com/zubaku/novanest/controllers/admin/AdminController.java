package com.zubaku.novanest.controllers.admin;

import com.zubaku.novanest.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
  public BorderPane adminParent;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Model.getInstance()
        .getViewProcessor()
        .getAdminSelectedMenuItem()
        .addListener(
            (observable, oldValue, newValue) -> {
              // TODO: Add switch statement
              switch (newValue) {
                case CLIENTS -> adminParent.setCenter(
                    Model.getInstance().getViewProcessor().getClientsView());
                default -> adminParent.setCenter(
                    Model.getInstance().getViewProcessor().getCreateClientView());
              }
            });
  }
}
