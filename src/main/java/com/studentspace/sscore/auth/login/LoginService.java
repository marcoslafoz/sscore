package com.studentspace.sscore.auth.login;

import com.studentspace.sscore.domain.user.User;
import com.studentspace.sscore.security.PasswordEncryption;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PasswordEncryption passwordEncryption;

    @Transactional
    public User getUserByUsernameAndPassword(String username, String password) {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            Query<User> query = currentSession.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username);

            User user = query.getSingleResult();

            if (user != null && passwordEncryption.encryptPasswordMatch(password, user.getPassword())) {
                return user;
            }

        } catch (NoResultException e) {
            return null;
        }

        return null;
    }


}
