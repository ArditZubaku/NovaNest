package com.zubaku.novanest.controllers.client;

import com.zubaku.novanest.models.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class ClientController implements Initializable {
  public BorderPane clientParent;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Model.getInstance()
        .getViewProcessor()
        .getClientSelectedMenuItem()
        .addListener(
            (observable, oldValue, newValue) -> {
              switch (newValue) {
                case "Transaction" -> clientParent.setCenter(
                    Model.getInstance().getViewProcessor().getTransactionsView());
                default -> clientParent.setCenter(
                    Model.getInstance().getViewProcessor().getDashboardView());
              }
            });
  }
}
