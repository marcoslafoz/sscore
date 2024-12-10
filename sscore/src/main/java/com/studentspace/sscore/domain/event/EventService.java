package com.studentspace.sscore.domain.event;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void update(Event event) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(event);
    }

    @Transactional
    public Event load(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.find(Event.class, id);
    }

    @Transactional
    public void create(Event event) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(event);
    }

    @Transactional
    public void delete(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Event eventToDelete = currentSession.get(Event.class, id);
        if (eventToDelete != null) {
            currentSession.remove(eventToDelete);
        }
    }

    @Transactional
    public List<Event> getEventsByUserId(Long userId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Event> query = currentSession.createQuery("SELECT e FROM Event e WHERE e.user.id = :userId "
                + "ORDER BY e.start ASC", Event.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Transactional
    public List<Event> getUpcomingEventsByUserId(Long userId, int total) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Event> query = currentSession.createQuery(
                "SELECT e FROM Event e WHERE e.user.id = :userId " +
                        "AND e.start >= :currentDate ORDER BY e.start ASC", Event.class
        );
        query.setParameter("userId", userId);
        query.setParameter("currentDate", ZonedDateTime.now());
        query.setMaxResults(total);
        return query.getResultList();
    }



}
