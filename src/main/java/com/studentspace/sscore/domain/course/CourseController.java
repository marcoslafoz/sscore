package com.studentspace.sscore.domain.course;

import com.studentspace.sscore.domain.document.DocumentService;
import com.studentspace.sscore.domain.subject.SubjectService;
import com.studentspace.sscore.domain.task.TaskService;
import com.studentspace.sscore.domain.user.User;
import com.studentspace.sscore.domain.user.UserService;
import graphql.GraphQLException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Controller
@RestController
@Transactional
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @QueryMapping
    public List<Course> courseGetListByUser (@Argument Long userId){
        return courseService.getCourseListByUser(userId);
    }

    @QueryMapping
    public Course courseRead(@Argument Long courseId, @Argument Long userId){
        Course course = courseService.load(courseId);
        if(Objects.equals(course.getUser().getId(), userId)) return course;
        throw new GraphQLException();
    }

    @MutationMapping
    public boolean courseEdit(@Argument Course course){

        Course editedCourse = courseService.load(course.getId());
        editedCourse.setName(course.getName());
        editedCourse.setColor(course.getColor());
        courseService.update(editedCourse);

        return true;
    }

    @MutationMapping
    public boolean courseAdd(@Argument Long userId, @Argument Course course){
        Course newCourse = new Course();
        User user = userService.load(userId);

        if(user == null) throw new GraphQLException();

        newCourse.setUser(user);
        newCourse.setName(course.getName());
        newCourse.setColor(course.getColor());

        courseService.create(newCourse);

        return true;
    }

    @MutationMapping
    public boolean courseDelete(@Argument Long courseId) {
        courseService.delete(courseId);
        return true;
    }

}
