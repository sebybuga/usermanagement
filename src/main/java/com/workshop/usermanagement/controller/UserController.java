package com.workshop.usermanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.usermanagement.dto.UserDto;
import com.workshop.usermanagement.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }
    /*TODO add rights*/
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }
    
	/* TODO add rights */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)    
    public boolean deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
    
    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/all/{ids}")
    public List<UserDto> getUsersByIds(@PathVariable List<Integer> ids) {
        return userService.getAllUsersByIds(ids);
    }
    	
	
}
