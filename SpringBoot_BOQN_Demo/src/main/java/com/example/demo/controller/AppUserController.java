/**
 * 
 */
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AppUser;
import com.example.demo.service.AppUserService;

/**
 * @author mahasarathi
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/rest")
public class AppUserController {
 
	@Autowired
	private AppUserService appUserService;
 
    @GetMapping(name = "getAllUsers", value = "/users")
    public List<AppUser> getUsers() {
        return appUserService.getUsers();
    }
 
    @PostMapping(name = "addNewOrUpdateExistingUser", consumes = "application/json", value = "/users")
    public Long addUser(@RequestBody(required = true) AppUser user) {
        return appUserService.addUpdateUser(user);
    }

    @DeleteMapping(name = "deleteExistingUser", consumes = "application/json", value = "/users")
    public void deleteUser(@RequestBody(required = true) AppUser user) {
        appUserService.deleteUser(user);
    }
}
