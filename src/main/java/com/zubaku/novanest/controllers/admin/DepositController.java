package com.zubaku.novanest.controllers.admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
  public TextField payeeAddressTextField;
  public Button searchButton;
  public ListView resultListView;
  public TextField amountTextField;
  public Button depositButton;

  @Override
  public void initialize(URL location, ResourceBundle resources) {}
}
