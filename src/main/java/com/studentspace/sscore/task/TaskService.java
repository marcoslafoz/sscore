package com.studentspace.sscore.task;

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
    public List<Task> getTasksByUserId(Long userId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Task> query = currentSession.createQuery("SELECT t FROM Task t WHERE t.user.id = :userId", Task.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Transactional
    public List<Task> getTasksBySubjectId(Long subjectId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Task> query = currentSession.createQuery("SELECT t FROM Task t WHERE t.subject.id = :subjectId", Task.class);
        query.setParameter("subjectId", subjectId);
        return query.getResultList();
    }

}
