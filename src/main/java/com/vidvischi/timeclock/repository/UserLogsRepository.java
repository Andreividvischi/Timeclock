package com.vidvischi.timeclock.repository;

import java.util.List;

import com.vidvischi.timeclock.model.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogsRepository extends JpaRepository<UserLog, Integer>{

  List<UserLog> findAllByUserIdOrderByDayAsc(Integer userId);

  List<UserLog> findAllByUserIdAndDay(Integer userId, String day);

  List<UserLog> findAll();
}
