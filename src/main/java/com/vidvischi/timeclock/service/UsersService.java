package com.vidvischi.timeclock.service;

import java.util.List;
import java.util.Optional;

import com.vidvischi.timeclock.model.User;
import com.vidvischi.timeclock.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	
	public List<User> getList(){
		return usersRepository.findAll();
	}

	public Optional<User> getUserDetails(Integer id){
		return usersRepository.findById(id);
	}
}
