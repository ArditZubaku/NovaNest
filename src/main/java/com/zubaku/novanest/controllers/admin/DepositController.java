package com.zubaku.novanest.controllers.admin;

import com.zubaku.novanest.models.Client;
import com.zubaku.novanest.models.Model;
import com.zubaku.novanest.processors.ClientCellFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
  public TextField payeeAddressTextField;
  public Button searchButton;
  public ListView<Client> resultListView;
  public TextField amountTextField;
  public Button depositButton;
  public Label errorLabel;

  private Client client;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    searchButton.setOnAction(event -> onClientSearch());
    depositButton.setOnAction(event -> onDeposit());
  }

  private void onClientSearch() {
    ObservableList<Client> searchResults =
        Model.getInstance().searchClient(payeeAddressTextField.getText());
    if (searchResults.isEmpty()) {
      Timeline timeline =
          new Timeline(
              new KeyFrame(
                  Duration.seconds(3),
                  event -> errorLabel.setText("That user doesn't exist! Please try again.")));
      timeline.play();
      errorLabel.setText(null);
    } else {
      resultListView.setItems(searchResults);
      resultListView.setCellFactory(param -> new ClientCellFactory());
      client = searchResults.getFirst();
    }
  }

  private void onDeposit() {
    double amount = Double.parseDouble(amountTextField.getText());
    double newBalance = amount + client.savingsAccountProperty().get().balanceProperty().get();

    if (amountTextField.getText() != null) {
      Model.getInstance()
          .getRepository()
          .depositSavings(client.payeeAddressProperty().get(), newBalance);
    }

    emptyFields();
  }

  private void emptyFields() {
    payeeAddressTextField.setText(null);
    amountTextField.setText(null);
  }
}
