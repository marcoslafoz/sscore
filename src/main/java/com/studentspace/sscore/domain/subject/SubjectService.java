package com.studentspace.sscore.domain.subject;

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
public class SubjectService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void update(Subject subject) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(subject);
    }

    @Transactional
    public Subject load(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.find(Subject.class, id);
    }

    @Transactional
    public void create(Subject subject) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(subject);
    }

    @Transactional
    public List<Subject> getSubjectListByAcademicCourseId(Long courseId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Subject> query = currentSession.createQuery("SELECT s FROM Subject s WHERE s.academicCourse.id = :courseId", Subject.class);
        query.setParameter("courseId", courseId);

        List<Subject> subjectList =  query.getResultList();

        if(!subjectList.isEmpty()){
            return subjectList;
        } else {
            return new ArrayList<>();
        }
    }

    @Transactional
    public List<Subject> getSubjectListByUserId(Long userId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Subject> query = currentSession.createQuery("SELECT s FROM Subject s WHERE s.user.id = :userId", Subject.class);
        query.setParameter("userId", userId);

        List<Subject> subjectList =  query.getResultList();

        if(!subjectList.isEmpty()){
            return subjectList;
        } else {
            return new ArrayList<>();
        }

    }


}
