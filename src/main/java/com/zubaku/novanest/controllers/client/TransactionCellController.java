package com.zubaku.novanest.controllers.client;

import com.zubaku.novanest.models.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {
  public FontAwesomeIconView incomingIcon;
  public FontAwesomeIconView outgoingIcon;
  public Label transactionDateLabel;
  public Label transactionSenderLabel;
  public Label transactionReceiverLabel;
  public Label transactionAmountLabel;

  private final Transaction transaction;

  public TransactionCellController(Transaction transaction) {
    this.transaction = transaction;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {}
}
