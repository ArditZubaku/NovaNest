package com.zubaku.novanest;

import com.zubaku.novanest.models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
  @Override
  public void start(Stage stage) {
    Model.getInstance().getViewProcessor().showLoginWindow();
  }

  public static void main(String[] args) {
    launch();
  }
}
