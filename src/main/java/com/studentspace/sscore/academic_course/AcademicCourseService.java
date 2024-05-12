package com.studentspace.sscore.academic_course;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademicCourseService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<AcademicCourse> getAcademicCourseListByUserId(Long userId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<AcademicCourse> query = currentSession.createQuery("SELECT c FROM AcademicCourse c WHERE c.user.id = :userId", AcademicCourse.class);
        query.setParameter("userId", userId);

        List<AcademicCourse> courseList =  query.getResultList();

        if(!courseList.isEmpty()){
            return courseList;
        } else {
            return new ArrayList<>();
        }

    }
}
