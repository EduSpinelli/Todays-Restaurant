package com.restaurant.choice.security.exceptions;



public class AuthenticationException extends RuntimeException {
  private static final long serialVersionUID = 7472786415695006640L;

  public AuthenticationException(String message, Throwable cause) {
    super(message, cause);
  }

  public AuthenticationException(String message) {
    super(message);
  }
}
