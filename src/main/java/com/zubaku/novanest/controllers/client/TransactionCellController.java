package com.zubaku.novanest.controllers.client;

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

  @Override
  public void initialize(URL location, ResourceBundle resources) {}
}
