package com.vidvischi.timeclock.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class UserLogPK implements Serializable {
  protected Integer userId;
  protected String day;
}
