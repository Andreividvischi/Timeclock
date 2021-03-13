package com.vidvischi.timeclock.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.vidvischi.timeclock.TimeClockApplication;
import com.vidvischi.timeclock.exception.InvalidRequestException;
import com.vidvischi.timeclock.exception.RecordAllreadyExistsException;
import com.vidvischi.timeclock.model.UserLog;
import com.vidvischi.timeclock.repository.UserLogsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserLogsService {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	private UsersService usersService;

	@Autowired
	private UserLogsRepository userLogsRepository;

	public UserLog addUserLog(UserLog userLog){
		//TODO: add validations, this method could be use for correction purpose
		return userLogsRepository.save(userLog);
	}
	
	public List<UserLog> getLogsByUser(Integer userId){
		return userLogsRepository.findAllByUserIdOrderByDayAsc(userId);
	}

	public List<UserLog> getAllLogs(){
		//TODO transform day to date for a good order by
		return userLogsRepository.findAll(Sort.by("userId", "day"));
	}

	public UserLog login(Integer userId) throws RecordAllreadyExistsException, InvalidRequestException {
  	Date now = Calendar.getInstance().getTime();
		final String day = dateFormat.format(now);

		validateDataForLogin(userId, day);

		return userLogsRepository.save(new UserLog(userId, day, now, null));
	}

	private void validateDataForLogin(Integer userId, String day) throws RecordAllreadyExistsException, InvalidRequestException {
		//TODO: this could be handled by FK error
		if (usersService.getUserDetails(userId).isEmpty()) {
			LOGGER.error(TimeClockApplication.USER_DO_NOT_EXISTS);
			throw new InvalidRequestException(TimeClockApplication.USER_DO_NOT_EXISTS);
		}

		//TODO: this could be handled by PK error
		if (!userLogsRepository.findAllByUserIdAndDay(userId, day).isEmpty()) {
			LOGGER.error(TimeClockApplication.LOGIN_ALREADY_DONE_ERROR);
			throw new RecordAllreadyExistsException(TimeClockApplication.LOGIN_ALREADY_DONE_ERROR);
		}
	}

	public UserLog logout(Integer userId) throws InvalidRequestException, RecordAllreadyExistsException {
		Date now = Calendar.getInstance().getTime();
		final String day = dateFormat.format(now);

		//TODO: Give possibility to logout next day
		UserLog userLog = getExistingRecordForLogout(userId, day);
		userLog.setLogout(now);

		return userLogsRepository.save(userLog);
	}

	private UserLog getExistingRecordForLogout(Integer userId, String day) throws InvalidRequestException, RecordAllreadyExistsException {
		final List<UserLog> logsByUserIdAndDay = userLogsRepository.findAllByUserIdAndDay(userId, day);
		if (logsByUserIdAndDay.isEmpty()) {
			LOGGER.error(TimeClockApplication.LOGIN_IS_MISSING_ERROR);
			throw new InvalidRequestException(TimeClockApplication.LOGIN_IS_MISSING_ERROR);
		}

		final UserLog userLog = logsByUserIdAndDay.get(0);
		if (userLog.getLogout() != null) {
			LOGGER.error(TimeClockApplication.LOGOUT_ALREADY_DONE_ERROR);
			throw new RecordAllreadyExistsException(TimeClockApplication.LOGOUT_ALREADY_DONE_ERROR);
		}

		return userLog;
	}
}
