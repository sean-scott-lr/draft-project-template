package com.logrhythm.core.model.v1;

import java.util.List;

public class Response {

  private String requestId;
  private Error error = null;

  public static Response empty() {
    return new Response();
  }

  public static Response error(int status, String message) {
    return error(new Error(status, message, null));
  }

  public static Response error(
      int status, String message, List<ValidationFailure> validationFailures) {
    return error(new Error(status, message, validationFailures));
  }

  public static Response error(Error error) {
    Response response = new Response();
    response.setError(error);
    return response;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }

  public static class Error {

    private int status;
    private String message;
    private List<ValidationFailure> validationFailures;

    protected Error() {}

    public Error(int status, String message, List<ValidationFailure> validationFailures) {
      super();
      this.status = status;
      this.message = message;
      this.validationFailures = validationFailures;
    }

    public int getStatus() {
      return status;
    }

    public String getMessage() {
      return message;
    }

    public List<ValidationFailure> getValidationFailures() {
      return validationFailures;
    }
  }

  public static class ValidationFailure {
    private String field = null;
    private String message = null;

    public ValidationFailure(String field, String message) {
      this.field = field;
      this.message = message;
    }

    public String getField() {
      return field;
    }

    public String getMessage() {
      return message;
    }
  }
}
