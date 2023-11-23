package com.zubaku.novanest.controllers.client;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.zubaku.novanest.models.Model;
import com.zubaku.novanest.models.Transaction;
import com.zubaku.novanest.processors.TransactionCellFactory;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class DashboardController implements Initializable {
  public Text usernameText;
  public Label loginDate;
  public Label checkingBalance;
  public Label checkingAccountNumber;
  public Label savingsBalance;
  public Label savingsAccountNumber;
  public Text incomeLabel;
  public Text expensesLabel;
  public ListView<Transaction> transactionsListView;
  public TextField payeeTextField;
  public TextField amountTextField;
  public TextArea messageTextArea;
  public Button sendMoneyButton;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    bindData();
    initLatestTransactionsList();
    transactionsListView.setItems(Model.getInstance().getLatestTransactions());
    transactionsListView.setCellFactory(param -> new TransactionCellFactory());
  }

  private void bindData() {
    usernameText
        .textProperty()
        .bind(Bindings.concat("Hi, ").concat(Model.getInstance().getClient().firstNameProperty()));

    loginDate.setText("Today, " + LocalDate.now());
    checkingBalance
        .textProperty()
        .bind(
            Model.getInstance()
                .getClient()
                .checkingAccountProperty()
                .get()
                .balanceProperty()
                .asString());
    checkingAccountNumber
        .textProperty()
        .bind(
            Model.getInstance()
                .getClient()
                .checkingAccountProperty()
                .get()
                .accountNumberProperty());
    savingsBalance
        .textProperty()
        .bind(
            Model.getInstance()
                .getClient()
                .savingsAccountProperty()
                .get()
                .balanceProperty()
                .asString());
    savingsAccountNumber
        .textProperty()
        .bind(
            Model.getInstance().getClient().savingsAccountProperty().get().accountNumberProperty());
  }

  private void initLatestTransactionsList() {
    if (Model.getInstance().getLatestTransactions().isEmpty()) {
      Model.getInstance().setLatestTransactions();
    }
  }
}
