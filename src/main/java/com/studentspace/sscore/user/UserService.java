package com.studentspace.sscore.user;

import com.studentspace.sscore.utils.PasswordEncryption;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<User> getUserList() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Transactional
    public User getUserById(Integer id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.find(User.class, id);
    }

    @Transactional
    public boolean save(User user) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> getEmailOrUsernameQuery = currentSession.createQuery("SELECT u FROM User u WHERE u.email = :email OR u.username = :username", User.class);

        getEmailOrUsernameQuery.setParameter("email", user.getEmail());
        getEmailOrUsernameQuery.setParameter("username", user.getUsername());

        User existingUser;

        try {
            existingUser = getEmailOrUsernameQuery.getSingleResult();
        } catch (NoResultException e) {
            existingUser = null;
        }

        if (existingUser == null || existingUser.getId().equals(user.getId())) {
            String encryptedPassword = PasswordEncryption.encryptPassword(user.getPassword());
            user.setPassword(encryptedPassword);
            currentSession.saveOrUpdate(user);
            return true;
        }

        return false;
    }

}
