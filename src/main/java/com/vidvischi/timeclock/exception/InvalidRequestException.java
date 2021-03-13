package com.vidvischi.timeclock.exception;

public class InvalidRequestException extends Exception{
  public InvalidRequestException()
  {
    super();
  }

  public InvalidRequestException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public InvalidRequestException(String message)
  {
    super(message);
  }

  public InvalidRequestException(Throwable cause)
  {
    super(cause);
  }
}
