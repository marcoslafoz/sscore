package com.studentspace.sscore.subject;

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
}
