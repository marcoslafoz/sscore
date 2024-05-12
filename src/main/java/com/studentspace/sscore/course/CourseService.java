package com.studentspace.sscore.course;

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
    public List<Course> getCourseListByUserId(Long userId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Course> query = currentSession.createQuery("SELECT c FROM Course c WHERE c.user.id = :userId", Course.class);
        query.setParameter("userId", userId);

        List<Course> courseList =  query.getResultList();

        if(!courseList.isEmpty()){
            return courseList;
        } else {
            return new ArrayList<>();
        }

    }
}
