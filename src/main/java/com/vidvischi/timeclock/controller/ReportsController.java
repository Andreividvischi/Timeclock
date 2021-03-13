package com.vidvischi.timeclock.controller;

import java.util.List;

import com.vidvischi.timeclock.model.UserLog;
import com.vidvischi.timeclock.service.UserLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "reports")
@RestController
public class ReportsController {
	//TODO: add Spring Security for user authentication

	@Autowired
	private UserLogsService userLogsService;

	@GetMapping(value = "getAllLogs")
	public List<UserLog> listAll() {
		return userLogsService.getAllLogs();
	}

	@GetMapping(value = "getLogsByUser/{userId}")
	public List<UserLog> listAllByUser(@PathVariable Integer userId) {
		return userLogsService.getLogsByUser(userId);
	}
}