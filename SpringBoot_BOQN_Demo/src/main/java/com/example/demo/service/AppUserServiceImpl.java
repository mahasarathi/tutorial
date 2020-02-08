/**
 * 
 */
package com.example.demo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AppUser;
import com.example.demo.enums.AppUserStatus;
import com.example.demo.repository.AppUserRepository;

/**
 * @author mahasarathi
 *
 */
@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUserRepository appUserRepository;

	public List<AppUser> getUsers() {
		return (List<AppUser>) appUserRepository.findAll();
	}

	public Long addUpdateUser(AppUser user) {
		Long appUserId = user.getId();
		AppUser savedUser = appUserRepository.save(user);
		if (Objects.nonNull(savedUser)) {
			appUserId = savedUser.getId();
		}
		return appUserId;
	}

	public void deleteUser(AppUser user) {
		user.setStatus(AppUserStatus.DELETED);
		appUserRepository.save(user);
	}
}
