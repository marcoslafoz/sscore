package com.studentspace.sscore.controller;

import com.studentspace.sscore.model.User;
import com.studentspace.sscore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public User save(@RequestBody User userObj) {
		userService.save(userObj);
		return userObj;
	}
	
	@GetMapping("/user")
	public List<User> get(){
		return userService.get();
	}
	
	@GetMapping("/user/{id}")
	public User get(@PathVariable int id) {
		User userObj = userService.get(id);
		if(userObj == null) {
			throw new RuntimeException("User not found for the Id:"+id);
		}
		return userObj;
	}
	
	@PutMapping("/user")
	public User update(@RequestBody User employeeObj) {
		userService.save(employeeObj);
		return employeeObj;
	}
	
	@DeleteMapping("/user/{id}")
	public String delete(@PathVariable int id) {
		userService.delete(id);
		return "User has been deleted with id:"+id;
	}

	@QueryMapping
	public User getUserById(@Argument int id) {
		return userService.get(id);
	}
}
