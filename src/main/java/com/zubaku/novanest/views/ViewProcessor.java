package com.zubaku.novanest.views;

import com.zubaku.novanest.controllers.client.ClientController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.logging.Logger;
import java.util.logging.Level;

public class ViewProcessor {
  private static final Logger LOGGER = Logger.getLogger(ViewProcessor.class.getName());
  // Client Views
  private AnchorPane dashboardView;
  private AnchorPane transactionsView;

  public ViewProcessor() {}

  public AnchorPane getDashboardView() {
    if (dashboardView == null) {
      try {
        dashboardView =
            new FXMLLoader(getClass().getResource("/fxml/client/Dashboard.fxml")).load();
      } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Error loading dashboard view", e);
      }
    }
    return dashboardView;
  }

  public AnchorPane getTransactionsView() {
    if (transactionsView == null) {
      try {
        transactionsView =
            new FXMLLoader(getClass().getResource("/fxml/client/Transactions.fxml")).load();
      } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Error loading transactions view", e);
      }
    }
    return transactionsView;
  }

  public void showLoginWindow() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
    createStage(loader);
  }

  public void showClientWindow() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/client/Client.fxml"));
    ClientController clientController = new ClientController();
    loader.setController(clientController);
    createStage(loader);
  }

  private void createStage(FXMLLoader loader) {
    Scene scene = null;
    try {
      scene = new Scene(loader.load());
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Error creating stage", e);
    }
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("NovaNest Bank");
    stage.show();
  }

  public void closeStage(Stage stage) {
    stage.close();
  }
}
