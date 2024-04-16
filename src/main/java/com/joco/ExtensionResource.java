package com.joco;

import org.junit.jupiter.api.extension.ExtensionContext;

public class ExtensionResource<T> implements ExtensionContext.Store.CloseableResource {
  private final T value;

  public ExtensionResource(T value) {
    this.value = value;
  }

  @Override
  public void close() throws Throwable {
    System.out.println("closing clock...");
  }

  public T getValue() {
    return value;
  }

}
