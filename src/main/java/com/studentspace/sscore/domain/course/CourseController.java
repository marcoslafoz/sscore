package com.studentspace.sscore.domain.course;

import com.studentspace.sscore.common.ObjectType;
import com.studentspace.sscore.domain.document.Document;
import com.studentspace.sscore.domain.document.DocumentService;
import com.studentspace.sscore.domain.subject.Subject;
import com.studentspace.sscore.domain.subject.SubjectService;
import com.studentspace.sscore.domain.task.Task;
import com.studentspace.sscore.domain.task.TaskService;
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

}
