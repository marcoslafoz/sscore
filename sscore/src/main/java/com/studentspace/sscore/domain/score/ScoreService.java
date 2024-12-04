package com.studentspace.sscore.domain.score;

import com.studentspace.sscore.domain.task.Task;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void update(Score score) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(score);
    }

    @Transactional
    public Score load(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.find(Score.class, id);
    }

    @Transactional
    public void create(Score score) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(score);
    }

    @Transactional
    public void delete(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Score scoreToDelete = currentSession.get(Score.class, id);
        if (scoreToDelete != null) {
            currentSession.remove(scoreToDelete);
        }
    }

    @Transactional
    public List<Score> getScoresBySubjectId(Long subjectId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Score> query = currentSession.createQuery("SELECT s FROM Score s WHERE s.subject.id = :subjectId " +
                "ORDER BY s.date ASC", Score.class);
        query.setParameter("subjectId", subjectId);
        return query.getResultList();
    }

    @Transactional
    public List<Score> getScoresByUserId(Long userId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Score> query = currentSession.createQuery("SELECT s FROM Score s WHERE s.user.id = :userId " +
                "ORDER BY s.date ASC", Score.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

}
