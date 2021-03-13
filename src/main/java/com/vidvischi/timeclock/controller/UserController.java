package com.vidvischi.timeclock.controller;

import java.util.List;

import com.vidvischi.timeclock.model.User;
import com.vidvischi.timeclock.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "user")
@RestController
public class UserController {
	//TODO: add Spring Security for user authentication

	@Autowired
	private UsersService usersService;

	@GetMapping(value = "list")
	public List<User> list() {
		return usersService.getList();
	}
}