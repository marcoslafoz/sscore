package com.studentspace.sscore;

import com.studentspace.sscore.model.User;
import com.studentspace.sscore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class SscoreApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {

		SpringApplication.run(SscoreApplication.class, args);


	}

}
