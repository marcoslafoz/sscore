package com.studentspace.sscore.domain.task;

import com.studentspace.sscore.domain.academic_course.AcademicCourse;
import com.studentspace.sscore.domain.academic_course.AcademicCourseService;
import com.studentspace.sscore.domain.subject.Subject;
import com.studentspace.sscore.domain.subject.SubjectService;
import com.studentspace.sscore.domain.user.User;
import com.studentspace.sscore.domain.user.UserService;
import com.studentspace.sscore.utils.DateConverter;
import graphql.GraphQLException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RestController
@Transactional
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private AcademicCourseService academicCourseService;

    @Autowired
    private SubjectService subjectService;

    @QueryMapping
    public List<Task> getTasksByUserId(@Argument Long userId) {
        return taskService.getTasksByUserId(userId);
    }

    @QueryMapping
    public List<Task> getTasksBySubjectId(@Argument Long subjectId) {
        return taskService.getTasksBySubjectId(subjectId);
    }

    @QueryMapping
    public List<Task> getTasksByAcademicCourseId(@Argument Long academicCourseId) {
        return taskService.getTasksByAcademicCourseId(academicCourseId);
    }

    @MutationMapping
    public boolean setTaskChecked(@Argument Long taskId, @Argument Boolean checked) {
        Task task = taskService.load(taskId);
        if (task == null) throw new GraphQLException();
        task.setChecked(checked);
        taskService.update(task);

        return true;
    }

    @MutationMapping
    public boolean addTask(@Argument Long userId, @Argument Task task) {

        User user = userService.load(userId);

        if (user == null) return false;

        Task newTask = new Task();

        newTask.setTitle(task.getTitle());
        newTask.setChecked(false);
        newTask.setUser(user);

        if (task.getDescription() != null) newTask.setDescription(task.getDescription());

        if (task.getDate() != null) {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(task.getDate().toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            newTask.setDate(zonedDateTime);
        }

        if (task.getAcademicCourse() != null && task.getAcademicCourse().getId() != null && task.getAcademicCourse().getId() != 0) {
            AcademicCourse academicCourse = new AcademicCourse();
            academicCourse.setId(task.getAcademicCourse().getId());
            newTask.setAcademicCourse(academicCourse);
        }

        if (task.getSubject() != null && task.getSubject().getId() != null && task.getSubject().getId() != 0) {
            Subject subject = new Subject();
            subject.setId(task.getSubject().getId());
            newTask.setSubject(subject);
        }

        taskService.create(newTask);

        return true;
    }
}
