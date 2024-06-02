package com.studentspace.sscore.domain.course;

import com.studentspace.sscore.domain.document.Document;
import com.studentspace.sscore.domain.task.Task;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void update(Course course) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(course);
    }

    @Transactional
    public Course load(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.find(Course.class, id);
    }

    @Transactional
    public void create(Course course) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(course);
    }

    @Transactional
    public void delete(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Course courseToDelete = currentSession.get(Course.class, id);
        if (courseToDelete != null) currentSession.remove(courseToDelete);
    }

    @Transactional
    public List<Course> getCourseListByUser(Long userId){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Course> query = currentSession.createQuery("SELECT c FROM Course c WHERE c.user.id = :userId ");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

}
