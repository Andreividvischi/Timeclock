package com.vidvischi.timeclock.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_logs")
@IdClass(UserLogPK.class)
public class UserLog {

	@Id
	@Column(name = "user_id")
	private int userId;

	@Id
	@Column(name = "day")
	private String day;

	@Column(name = "login")
	private Date login;

	@Column(name = "logout")
	private Date logout;

}
