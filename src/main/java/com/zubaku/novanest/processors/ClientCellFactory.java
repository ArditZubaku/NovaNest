package com.zubaku.novanest.processors;

import com.zubaku.novanest.controllers.client.ClientCellController;
import com.zubaku.novanest.models.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class ClientCellFactory extends ListCell<Client> {
  @Override
  protected void updateItem(Client item, boolean empty) {
    super.updateItem(item, empty);
    if (empty) {
      setText(null);
      setGraphic(null);
    } else {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/client/ClientCell.fxml"));
      ClientCellController clientCellController = new ClientCellController(item);
      loader.setController(clientCellController);
      try{
        setGraphic(loader.load());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
