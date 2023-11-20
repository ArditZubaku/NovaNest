package com.zubaku.novanest.views;

import com.zubaku.novanest.controllers.admin.AdminController;
import com.zubaku.novanest.controllers.client.ClientController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewProcessor {
  private static final Logger LOGGER = Logger.getLogger(ViewProcessor.class.getName());
  // Client Views
  private final StringProperty clientSelectedMenuItem;
  private AnchorPane dashboardView;
  private AnchorPane transactionsView;
  private AnchorPane accountsView;

  // Admin Views
  private final StringProperty adminSelectedMenuItem;
  private AnchorPane createClientView;

  public ViewProcessor() {
    this.clientSelectedMenuItem = new SimpleStringProperty();
    this.adminSelectedMenuItem = new SimpleStringProperty();
  }

  /*
   * Client Views Section
   */

  public StringProperty getClientSelectedMenuItem() {
    return clientSelectedMenuItem;
  }

  public AnchorPane getDashboardView() {
    if (dashboardView == null) {
      try {
        dashboardView =
            new FXMLLoader(getClass().getResource("/views/client/Dashboard.fxml")).load();
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
            new FXMLLoader(getClass().getResource("/views/client/Transactions.fxml")).load();
      } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Error loading transactions view", e);
      }
    }
    return transactionsView;
  }

  public AnchorPane getAccountsView() {
    if (accountsView == null) {
      try {
        accountsView = new FXMLLoader(getClass().getResource("/views/client/Accounts.fxml")).load();
      } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Error loading accounts view", e);
      }
    }
    return accountsView;
  }

  public void showClientWindow() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/client/Client.fxml"));
    ClientController clientController = new ClientController();
    loader.setController(clientController);
    createStage(loader);
  }

  /*
   * Admin Views Section
   */

  public StringProperty getAdminSelectedMenuItem() {
    return adminSelectedMenuItem;
  }

  public AnchorPane getCreateClientView() {
    if (createClientView == null) {
      try {
        createClientView =
            new FXMLLoader(getClass().getResource("/views/client/CreateClient.fxml")).load();
      } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Error loading createClient view", e);
      }
    }
    return createClientView;
  }

  public void showAdminWindow() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/admin/Admin.fxml"));
    AdminController adminController = new AdminController();
    loader.setController(adminController);
    createStage(loader);
  }

  public void showLoginWindow() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Login.fxml"));
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
