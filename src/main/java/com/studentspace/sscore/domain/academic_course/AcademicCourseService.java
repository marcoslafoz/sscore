package com.studentspace.sscore.domain.academic_course;

import com.studentspace.sscore.domain.subject.Subject;
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
    public void update(AcademicCourse academicCourse) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(academicCourse);
    }

    @Transactional
    public AcademicCourse load(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.find(AcademicCourse.class, id);
    }

    @Transactional
    public void create(AcademicCourse academicCourse) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(academicCourse);
    }

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
