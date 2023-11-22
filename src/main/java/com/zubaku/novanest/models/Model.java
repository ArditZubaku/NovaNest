package com.zubaku.novanest.models;

import com.zubaku.novanest.processors.ViewProcessor;
import com.zubaku.novanest.repository.DatabaseDriver;

public class Model {
  private final ViewProcessor viewProcessor;
  private final DatabaseDriver databaseDriver;

  // Private constructor.
  private Model() {
    this.viewProcessor = new ViewProcessor();
    this.databaseDriver = new DatabaseDriver();
  }

  // Static inner class for holding the instance.
  private static class Holder {
    private static final Model INSTANCE = new Model();
  }

  // Public method to get the instance.
  public static Model getInstance() {
    return Holder.INSTANCE;
  }

  public ViewProcessor getViewProcessor() {
    return viewProcessor;
  }

  private DatabaseDriver getDatabaseDriver() {
    return databaseDriver;
  }
}
