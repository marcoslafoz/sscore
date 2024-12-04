package com.studentspace.sscore.domain.user;

import com.studentspace.sscore.domain.task.Task;
import com.studentspace.sscore.security.PasswordEncryption;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PasswordEncryption passwordEncryption;

    public void update(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(user);
    }

    @Transactional
    public User load(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.find(User.class, id);
    }

    @Transactional
    public void delete(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Task userToDelete = currentSession.get(Task.class, id);
        if (userToDelete != null) {
            currentSession.remove(userToDelete);
        }
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
            String encryptedPassword = passwordEncryption.encryptPassword(user.getPassword());
            user.setPassword(encryptedPassword);
            currentSession.persist(user);
            return true;
        }

        return false;
    }

    @Transactional
    public Optional<User> getUserByUsername(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        try {
            User user = query.getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Transactional
    public List<Avatar> getAvatarList() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Avatar> query = currentSession.createQuery("SELECT a FROM Avatar a ORDER BY a.title ASC", Avatar.class);
        return query.getResultList();
    }

}
