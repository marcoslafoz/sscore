package com.studentspace.sscore.task;

import com.studentspace.sscore.user.User;
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
    public List<Task> getTasksByUserId(Long userId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Task> query = currentSession.createQuery("SELECT t FROM Task t WHERE t.user.id = :userId "
                + "ORDER BY t.checked ASC, t.date ASC", Task.class);
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

    @Transactional
    public List<Task> getTasksByAcademicCourseId(Long academicCourseId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Task> query = currentSession.createQuery("SELECT t FROM Task t WHERE t.academicCourse.id = :academicCourseId ", Task.class);
        query.setParameter("academicCourseId", academicCourseId);
        return query.getResultList();
    }


}
