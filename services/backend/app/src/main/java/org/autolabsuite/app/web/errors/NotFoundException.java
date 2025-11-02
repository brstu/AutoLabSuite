package org.autolabsuite.app.web.errors;

/** Simple 404 exception for controllers. */
public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) { super(message); }
}
