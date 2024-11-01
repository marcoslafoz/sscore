package com.studentspace.sscore.domain.task;

import com.studentspace.sscore.domain.course.Course;
import com.studentspace.sscore.domain.course.CourseService;
import com.studentspace.sscore.domain.subject.Subject;
import com.studentspace.sscore.domain.subject.SubjectService;
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

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

@Controller
@RestController
@Transactional
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @QueryMapping
    public List<Task> taskGetListByUser(@Argument Long userId) {
        return taskService.getTasksByUserId(userId);
    }

    @QueryMapping
    public List<Task> taskGetPendingListByUser(@Argument Long userId) {
        return taskService.getPendingTasksByUserId(userId);
    }

    @QueryMapping
    public List<Task> taskGetListByCourse(@Argument Long courseId) {
        return taskService.getTasksByCourseId(courseId);
    }

    @MutationMapping
    public boolean taskSetChecked(@Argument Long taskId, @Argument Boolean checked) {
        Task task = taskService.load(taskId);
        if (task == null) throw new GraphQLException();
        task.setChecked(checked);
        taskService.update(task);

        return true;
    }

    @MutationMapping
    public boolean taskAdd(@Argument Long userId, @Argument Task task) {

        User user = userService.load(userId);

        if (user == null) return false;

        Task newTask = new Task();

        newTask.setTitle(task.getTitle());
        newTask.setChecked(false);
        newTask.setUser(user);

        if (task.getDescription() != null) newTask.setDescription(task.getDescription());

        if (task.getDate() != null) {
            try {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(task.getDate().toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                newTask.setDate(zonedDateTime);
            } catch (DateTimeParseException e) {
                return false;
            }
        }

        if (task.getCourse().getId() != null && task.getCourse().getId() != 0) {
            Course course = courseService.load(task.getCourse().getId());
            newTask.setCourse(course);

            if (task.getSubject().getId() != null && task.getSubject().getId() != 0) {
                Subject subject = subjectService.load(task.getSubject().getId());
                if (!Objects.equals(subject.getCourse().getId(), course.getId())) {
                    subject = null;
                }
                newTask.setSubject(subject);
            }else{
                newTask.setSubject(null);
            }

        }else{
            newTask.setCourse(null);
            newTask.setSubject(null);
        }

        taskService.create(newTask);

        return true;
    }

    @MutationMapping
    public boolean taskEdit(@Argument Task task) {

        Task updatedTask = taskService.load(task.getId());

        if (updatedTask == null) return false;

        updatedTask.setTitle(task.getTitle());
        updatedTask.setDescription(task.getDescription());

        if (task.getDate() != null) {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(task.getDate().toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            updatedTask.setDate(zonedDateTime);
        } else {
            updatedTask.setDate(null);
        }

        if (task.getCourse().getId() != null && task.getCourse().getId() != 0) {
            Course course = courseService.load(task.getCourse().getId());
            updatedTask.setCourse(course);

            if (task.getSubject().getId() != null && task.getSubject().getId() != 0) {
                Subject subject = subjectService.load(task.getSubject().getId());
                if (!Objects.equals(subject.getCourse().getId(), course.getId())) {
                    subject = null;
                }
                updatedTask.setSubject(subject);
            }else{
                updatedTask.setSubject(null);
            }

        }else{
            updatedTask.setCourse(null);
            updatedTask.setSubject(null);
        }


        taskService.update(updatedTask);
        return true;
    }

    @MutationMapping
    public boolean taskDelete(@Argument Long taskId) {
        taskService.delete(taskId);
        return true;
    }

    @MutationMapping
    public Integer taskDeleteCheckedList(@Argument Long userId) {
        return taskService.deleteTasksByUserIdAndChecked(userId);
    }
}
