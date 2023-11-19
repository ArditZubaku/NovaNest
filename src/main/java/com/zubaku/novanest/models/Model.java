package com.zubaku.novanest.models;

import com.zubaku.novanest.views.ViewProcessor;

public class Model {
  private final ViewProcessor viewProcessor;

  // Private constructor.
  private Model() {
    this.viewProcessor = new ViewProcessor();
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
}
