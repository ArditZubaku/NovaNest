package com.zubaku.novanest.controllers.client;

import java.net.URL;
import java.util.ResourceBundle;
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
  public ListView transactionsListView;
  public TextField payeeTextField;
  public TextField amountTextField;
  public TextArea messageTextArea;
  public Button sendMoneyButton;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }
}
