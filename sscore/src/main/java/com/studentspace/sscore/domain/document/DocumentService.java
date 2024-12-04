package com.studentspace.sscore.domain.document;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void update(Document document) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(document);
    }

    @Transactional
    public Document load(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.find(Document.class, id);
    }

    @Transactional
    public void create(Document document) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(document);
    }

    @Transactional
    public void delete(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Document documentToDelete = currentSession.get(Document.class, id);
        if (documentToDelete != null) currentSession.remove(documentToDelete);
    }

    @Transactional
    public List<Document> getDocumentsByUserId(Long userId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Document> query = currentSession.createQuery("SELECT d FROM Document d WHERE d.user.id = :userId ", Document.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }


}
