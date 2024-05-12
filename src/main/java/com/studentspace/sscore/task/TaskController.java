package com.studentspace.sscore.task;

import com.studentspace.sscore.subject.Subject;
import com.studentspace.sscore.subject.SubjectService;
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
public class TaskController {

    @Autowired
    private TaskService taskService;

    @QueryMapping
    public List<Task> getTasksByUserId(@Argument Long userId) {
        return taskService.getTasksByUserId(userId);
    }

    @QueryMapping
    public List<Task> getTasksBySubjectId(@Argument Long subjectId){
        return taskService.getTasksBySubjectId(subjectId);
    }

}
