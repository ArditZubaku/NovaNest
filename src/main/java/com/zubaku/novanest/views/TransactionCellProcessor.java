package com.zubaku.novanest.views;

import com.zubaku.novanest.controllers.client.TransactionCellController;
import com.zubaku.novanest.models.Transaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class TransactionCellProcessor extends ListCell<Transaction> {
  @Override
  protected void updateItem(Transaction item, boolean empty) {
    super.updateItem(item, empty);
    if (empty) {
      setText(null);
      setGraphic(null);
    } else {
      FXMLLoader loader =
          new FXMLLoader(getClass().getResource("/views/client/TransactionCell.fxml"));
      TransactionCellController transactionCellController = new TransactionCellController(item);
      loader.setController(transactionCellController);
      setText(null);
      try {
        setGraphic(loader.load());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
