/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.AppUser;

/**
 * @author mahasarathi
 *
 */
public interface AppUserService {

	List<AppUser> getUsers();

	Long addUpdateUser(AppUser user);

	void deleteUser(AppUser user);
}
