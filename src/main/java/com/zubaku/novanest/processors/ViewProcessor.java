package com.zubaku.novanest.processors;

import com.zubaku.novanest.controllers.admin.AdminController;
import com.zubaku.novanest.controllers.client.ClientController;
import com.zubaku.novanest.utils.enums.AccountType;
import com.zubaku.novanest.utils.enums.AdminMenuOptions;
import com.zubaku.novanest.utils.enums.ClientMenuOptions;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewProcessor {
  private static final Logger LOGGER = Logger.getLogger(ViewProcessor.class.getName());

  private AccountType logInAccountType;

  // Client Views
  private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
  private AnchorPane dashboardView;
  private AnchorPane transactionsView;
  private AnchorPane accountsView;

  // Admin Views
  private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
  private AnchorPane createClientView;
  private AnchorPane clientsView;
  private AnchorPane depositView;

  public ViewProcessor() {
    this.logInAccountType = AccountType.CLIENT;
    this.clientSelectedMenuItem = new SimpleObjectProperty<>();
    this.adminSelectedMenuItem = new SimpleObjectProperty<>();
  }

  public AccountType getLogInAccountType() {
    return logInAccountType;
  }

  public void setLogInAccountType(AccountType logInAccountType) {
    this.logInAccountType = logInAccountType;
  }

  /*
   * Client Views Section
   */

  public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
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

  public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
    return adminSelectedMenuItem;
  }

  public AnchorPane getCreateClientView() {
    if (createClientView == null) {
      try {
        createClientView =
            new FXMLLoader(getClass().getResource("/views/admin/CreateClient.fxml")).load();
      } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Error loading createClient view", e);
      }
    }
    return createClientView;
  }

  public AnchorPane getClientsView() {
    if (clientsView == null) {
      try {
        clientsView = new FXMLLoader(getClass().getResource("/views/admin/Clients.fxml")).load();
      } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Error loading clients view", e);
      }
    }
    return clientsView;
  }

  public AnchorPane getDepositView() {
    if (depositView == null) {
      try {
        depositView = new FXMLLoader(getClass().getResource("/views/admin/Deposit.fxml")).load();
      } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Error loading deposit view", e);
      }
    }
    return depositView;
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
    stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/icon.png"))));
    stage.setResizable(false);
    stage.setScene(scene);
    stage.setTitle("NovaNest Bank");
    stage.show();
  }

  public void closeStage(Stage stage) {
    stage.close();
  }
}
