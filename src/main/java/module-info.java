module com.zubaku.novanest {
  requires javafx.controls;
  requires javafx.fxml;
  requires de.jensd.fx.glyphs.fontawesome;
  requires java.sql;
  requires org.xerial.sqlitejdbc;

  opens com.zubaku.novanest to
      javafx.fxml;

  exports com.zubaku.novanest;
  exports com.zubaku.novanest.controllers;
  exports com.zubaku.novanest.controllers.admin;
  exports com.zubaku.novanest.controllers.client;
  exports com.zubaku.novanest.models;
  exports com.zubaku.novanest.views;
}
