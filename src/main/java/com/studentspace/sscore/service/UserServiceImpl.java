package com.studentspace.sscore.service;

import com.studentspace.sscore.dao.UserDAO;
import com.studentspace.sscore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	@Override
	public List<User> get() {
		return userDAO.get();
	}

	@Transactional
	@Override
	public User get(int id) {
		return userDAO.get(id);
	}

	@Transactional
	@Override
	public void save(User user) {
		userDAO.save(user);
	}

	@Transactional
	@Override
	public void delete(int id) {
		userDAO.delete(id);
	}

}
