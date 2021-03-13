package com.vidvischi.timeclock.controller;

import com.vidvischi.timeclock.exception.InvalidRequestException;
import com.vidvischi.timeclock.exception.RecordAllreadyExistsException;
import com.vidvischi.timeclock.model.UserLog;
import com.vidvischi.timeclock.service.UserLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "log")
@RestController
public class LogController {
  //TODO: add GlobalExceptionHandler with AOP
	//TODO: return message description as response in case of error, not only HTTP_STATUS

	@Autowired
	private UserLogsService userLogsService;

	@PostMapping(value = "login/{userId}")
	public ResponseEntity<UserLog> login(@PathVariable Integer userId) {
		try {
			return new ResponseEntity<>(userLogsService.login(userId), HttpStatus.OK);
		}
		catch (InvalidRequestException e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		catch (RecordAllreadyExistsException e) {
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
	}

	@PostMapping(value = "logout/{userId}")
	public ResponseEntity<UserLog> logout(@PathVariable Integer userId) {
		try {
			return new ResponseEntity<>(userLogsService.logout(userId), HttpStatus.OK);
		}
		catch (InvalidRequestException e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		catch (RecordAllreadyExistsException e) {
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
	}

	@PostMapping(value = "addLog")
	public UserLog addUserLog(@ModelAttribute UserLog userLog) {
		return userLogsService.addUserLog(userLog);
	}
}