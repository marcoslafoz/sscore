package com.studentspace.sscore.domain.task;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void update(Task task) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(task);
    }

    @Transactional
    public Task load(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.find(Task.class, id);
    }

    @Transactional
    public void create(Task task) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(task);
    }

    @Transactional
    public void delete(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Task taskToDelete = currentSession.get(Task.class, id);
        if (taskToDelete != null) {
            currentSession.remove(taskToDelete);
        }
    }

    @Transactional
    public List<Task> getTasksByUserId(Long userId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Task> query = currentSession.createQuery("SELECT t FROM Task t WHERE t.user.id = :userId "
                + "ORDER BY t.checked ASC, t.date ASC", Task.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Transactional
    public int deleteTasksByUserIdAndChecked(Long userId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("DELETE FROM Task WHERE user.id = :userId AND checked = true");
        query.setParameter("userId", userId);
        return query.executeUpdate();
    }


}
