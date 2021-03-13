package com.vidvischi.timeclock.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import com.vidvischi.timeclock.exception.InvalidRequestException;
import com.vidvischi.timeclock.exception.RecordAllreadyExistsException;
import com.vidvischi.timeclock.model.User;
import com.vidvischi.timeclock.model.UserLog;
import com.vidvischi.timeclock.repository.UserLogsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserLogsServiceTest {

  Integer userId = 1;

  @Mock
  private UsersService usersService;

  @Mock
  private UserLogsRepository userLogsRepository;

  @InjectMocks
  UserLogsService userLogsService;

  @Test
  void loginIsNotSaved() throws InvalidRequestException, RecordAllreadyExistsException {
    doReturn(Optional.of(new User())).when(usersService).getUserDetails(userId);

    userLogsService.login(userId);

    verify(userLogsRepository, Mockito.times(1)).save(any());
  }

  @Test
  void userIsNotCheckedAtLogin() {
    Assertions.assertThrows(InvalidRequestException.class, () -> userLogsService.login(userId));
  }

  @Test
  void existingLoginIsNotChecked() {
    doReturn(Optional.of(new User())).when(usersService).getUserDetails(userId);

    doReturn(Arrays.asList(new UserLog())).when(userLogsRepository).findAllByUserIdAndDay(anyInt(), anyString());

    Assertions.assertThrows(RecordAllreadyExistsException.class, () -> userLogsService.login(userId));
  }

  @Test
  void logoutIsNotSaved() throws InvalidRequestException, RecordAllreadyExistsException {
    doReturn(Arrays.asList(new UserLog())).when(userLogsRepository).findAllByUserIdAndDay(anyInt(), anyString());

    userLogsService.logout(userId);

    verify(userLogsRepository, Mockito.times(1)).save(any());
  }

  @Test
  void existingLoginIsNotCheckedAtLogout() {
    Assertions.assertThrows(InvalidRequestException.class, () -> userLogsService.logout(userId));
  }

  @Test
  void existingLogoutIsNotChecked() {
    final UserLog userLog = new UserLog();
    userLog.setLogout(new Date());

    doReturn(Arrays.asList(userLog)).when(userLogsRepository).findAllByUserIdAndDay(anyInt(), anyString());

    Assertions.assertThrows(RecordAllreadyExistsException.class, () -> userLogsService.logout(userId));
  }
}