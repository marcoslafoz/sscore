package com.studentspace.sscore.domain.subject;

import com.studentspace.sscore.domain.course.Course;
import com.studentspace.sscore.domain.course.CourseService;
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
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @QueryMapping
    public Subject subjectRead(@Argument Long subjectId, @Argument Long userId){
        Subject subject = subjectService.load(subjectId);
        if(Objects.equals(subject.getUser().getId(), userId)) return subject;
        throw new GraphQLException();
    }

    @MutationMapping
    public boolean subjectEdit(@Argument Subject subject){

        Subject editedSubject = subjectService.load(subject.getId());
        editedSubject.setName(subject.getName());
        subjectService.update(editedSubject);

        return true;
    }

    @MutationMapping
    public boolean subjectAdd(@Argument Long courseId, @Argument Subject subject){
        Subject newSubject = new Subject();

        Course course = courseService.load(courseId);
        User user = userService.load(course.getUser().getId());

        newSubject.setCourse(course);
        newSubject.setName(subject.getName());
        newSubject.setColor(subject.getColor());
        newSubject.setUser(user);


        subjectService.create(newSubject);

        return true;
    }

    @MutationMapping
    public boolean subjectDelete(@Argument Long subjectId) {
        subjectService.delete(subjectId);
        return true;
    }

}
