package com.studentspace.sscore.dao;

import com.studentspace.sscore.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.*;
import java.util.List;

@Repository
public class UserDAO  {

	@Autowired
	private EntityManager entityManager;

	public List<User> get() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> query = currentSession.createQuery("from User", User.class);
		List<User> list = query.getResultList();
		return list;
	}

	public User get(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		User employeeObj = currentSession.get(User.class, id);
		return employeeObj;
	}

	public void save(User user) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(user);
	}

	public void delete(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		User employeeObj = currentSession.get(User.class, id);
		currentSession.delete(employeeObj);
	}

}
