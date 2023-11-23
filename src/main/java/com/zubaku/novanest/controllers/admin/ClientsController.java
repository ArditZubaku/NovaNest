package com.zubaku.novanest.controllers.admin;

import com.zubaku.novanest.models.Client;
import com.zubaku.novanest.models.Model;
import com.zubaku.novanest.processors.ClientCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {
  public ListView<Client> clientsListView;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    initClientsList();
    clientsListView.setItems(Model.getInstance().getClients());
    clientsListView.setCellFactory(param -> new ClientCellFactory());
  }

  private void initClientsList() {
    if (Model.getInstance().getClients().isEmpty()) {
      Model.getInstance().setClients();
    }
  }
}
