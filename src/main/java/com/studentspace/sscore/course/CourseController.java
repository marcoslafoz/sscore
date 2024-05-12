package com.studentspace.sscore.course;

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
public class CourseController {

    @Autowired
    private CourseService courseService;

    @QueryMapping
    public List<Course> getCourseListByUserId(@Argument Long userId) {
        return courseService.getCourseListByUserId(userId);

    }

}
