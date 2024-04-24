package com.studentspace.sscore.service;

import com.studentspace.sscore.dao.UserDAO;
import com.studentspace.sscore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	public List<User> get() {
		return userDAO.get();
	}

	@Transactional
	public User get(int id) {
		return userDAO.get(id);
	}

	@Transactional
	public void save(User user) {
		userDAO.save(user);
	}

	@Transactional
	public void delete(int id) {
		userDAO.delete(id);
	}

}
