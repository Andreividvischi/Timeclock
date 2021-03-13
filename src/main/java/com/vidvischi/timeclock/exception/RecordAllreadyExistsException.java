package com.vidvischi.timeclock.exception;

public class RecordAllreadyExistsException extends Exception{
  public RecordAllreadyExistsException()
  {
    super();
  }

  public RecordAllreadyExistsException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public RecordAllreadyExistsException(String message)
  {
    super(message);
  }

  public RecordAllreadyExistsException(Throwable cause)
  {
    super(cause);
  }
}
