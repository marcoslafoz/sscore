package com.studentspace.sscore.academic_course;

import graphql.GraphQLException;
import io.leangen.graphql.annotations.GraphQLArgument;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.GraphQlClientException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import io.leangen.graphql.annotations.GraphQLQuery;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@Transactional
public class AcademicCourseController {

    @Autowired
    private AcademicCourseService courseService;

    @QueryMapping
    public List<AcademicCourse> getAcademicCourseListByUserId(@Argument Long userId) {
        try {
            return courseService.getAcademicCourseListByUserId(userId);
        } catch (Exception e) {
            throw new GraphQLException();
        }
    }

}
