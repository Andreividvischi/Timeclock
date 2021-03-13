package com.vidvischi.timeclock.repository;

import com.vidvischi.timeclock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer>{

}
