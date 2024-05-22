package com.studentspace.sscore.domain.subject;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@Transactional
public class SubjectController {

    @Autowired
    private SubjectService courseService;

    @QueryMapping
    public List<Subject> getSubjectListByAcademicCourseId(@Argument Long courseId) {
        return courseService.getSubjectListByAcademicCourseId(courseId);
    }

    @QueryMapping
    public List<Subject> getSubjectListByUserId(@Argument Long userId) {
        return courseService.getSubjectListByUserId(userId);
    }

}
