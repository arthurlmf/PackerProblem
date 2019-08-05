package com.arthurlmf.exception;

/**
 * Checked exception thrown when an error is found in the API.
 * @author arthurfarias
 *
 */

public class APIException extends Exception {

  public APIException(String message, Exception e) {
    super(message, e);
  }

  public APIException(String message) {
    super(message);
  }
}
