package com.studentspace.sscore.domain.academic_course;

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
public class AcademicCourseController {

    @Autowired
    private AcademicCourseService courseService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private SubjectService subjectService;

    @QueryMapping
    public List<AcademicCourse> getAcademicCourseListByUserId(@Argument Long userId) {
        return courseService.getAcademicCourseListByUserId(userId);
    }

    @MutationMapping
    public boolean removeAcademicCourseFromObject(@Argument String objectType, @Argument Long objectId ){

        if(Objects.equals(objectType, ObjectType.TASK)){
            Task task = taskService.load(objectId);
            task.setAcademicCourse(null);
            task.setSubject(null);
            taskService.update(task);
            return true;
        }

        if(Objects.equals(objectType, ObjectType.DOCUMENT)){
            Document document = documentService.load(objectId);
            document.setAcademicCourse(null);
            document.setSubject(null);
            documentService.update(document);
            return true;
        }

        throw new GraphQLException();
    }

    @MutationMapping
    public boolean removeSubjectFromObject(@Argument String objectType, @Argument Long objectId ){

        if(Objects.equals(objectType, ObjectType.TASK)){
            Task task = taskService.load(objectId);
            task.setSubject(null);
            taskService.update(task);
            return true;
        }

        if(Objects.equals(objectType, ObjectType.DOCUMENT)){
            Document document = documentService.load(objectId);
            document.setSubject(null);
            documentService.update(document);
            return true;
        }

        throw new GraphQLException();
    }

    @MutationMapping
    public boolean addAcademicCourseToObject(@Argument String objectType, @Argument Long objectId, @Argument Long academicCourseId){

        if(Objects.equals(objectType, ObjectType.TASK)){
            Task task = taskService.load(objectId);
            AcademicCourse academicCourse = courseService.load(academicCourseId);
            task.setAcademicCourse(academicCourse);
            if(!Objects.equals(task.getSubject().getAcademicCourse().getId(), academicCourseId)) task.setSubject(null);
            taskService.update(task);
            return true;
        }

        if(Objects.equals(objectType, ObjectType.DOCUMENT)){
            Document document = documentService.load(objectId);
            AcademicCourse academicCourse = courseService.load(academicCourseId);
            document.setAcademicCourse(academicCourse);
            if(!Objects.equals(document.getSubject().getAcademicCourse().getId(), academicCourseId)) document.setSubject(null);
            documentService.update(document);
            return true;
        }

        throw new GraphQLException();
    }

    @MutationMapping
    public boolean addSubjectToObject(@Argument String objectType, @Argument Long objectId, @Argument Long subjectId){

        if(Objects.equals(objectType, ObjectType.TASK)){

            Task task = taskService.load(objectId);
            if(task.getAcademicCourse() == null) throw new GraphQLException();
            Subject subject = subjectService.load(subjectId);
            task.setSubject(subject);
            taskService.update(task);
            return true;
        }

        if(Objects.equals(objectType, ObjectType.DOCUMENT)){

            Document document = documentService.load(objectId);
            if(document.getAcademicCourse() == null) throw new GraphQLException();
            Subject subject = subjectService.load(subjectId);
            document.setSubject(subject);
            documentService.update(document);
            return true;
        }

        throw new GraphQLException();
    }

}
