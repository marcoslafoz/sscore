package com.studentspace.sscore.domain.subject;

import com.studentspace.sscore.domain.document.Document;
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
    public void delete(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Subject subjectToDelete = currentSession.get(Subject.class, id);
        if (subjectToDelete != null) currentSession.remove(subjectToDelete);
    }

}
