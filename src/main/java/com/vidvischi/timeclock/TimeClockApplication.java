package com.vidvischi.timeclock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimeClockApplication {

  public static final String USER_DO_NOT_EXISTS = "User do not exists";
  public static final String LOGIN_ALREADY_DONE_ERROR = "Login already done";

  public static final String LOGIN_IS_MISSING_ERROR = "Login is missing!";
  public static final String LOGOUT_ALREADY_DONE_ERROR = "Logout already done!";

  //TODO: add dependency check plugin for clean up the pom.xml file

  public static void main(String[] args) {
		SpringApplication.run(TimeClockApplication.class, args);
	}

}
