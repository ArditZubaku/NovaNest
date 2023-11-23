package com.zubaku.novanest.controllers.client;

import com.zubaku.novanest.models.Model;
import com.zubaku.novanest.models.Transaction;
import com.zubaku.novanest.processors.TransactionCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {
  public ListView<Transaction> transactionsListView;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    initAllTransactionsList();
    transactionsListView.setItems(Model.getInstance().getAllTransactions());
    transactionsListView.setCellFactory(param -> new TransactionCellFactory());
  }

  private void initAllTransactionsList() {
    if (Model.getInstance().getAllTransactions().isEmpty()) {
      Model.getInstance().setAllTransactions();
    }
  }
}
