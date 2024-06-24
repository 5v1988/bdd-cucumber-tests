package enabler.exception;

public class TestRuntimeException extends RuntimeException{
  public TestRuntimeException() {}

  public TestRuntimeException(String message) {
    super(message);
  }

  public TestRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public TestRuntimeException(Throwable cause) {
    super(cause);
  }
}
