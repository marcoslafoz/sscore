package com.studentspace.sscore.domain.document;

import com.studentspace.sscore.domain.course.CourseService;
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

import java.util.List;
import java.util.Objects;

@Controller
@RestController
@Transactional
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectService subjectService;

    @QueryMapping
    public List<Document> documentGetListByUser(@Argument Long userId) {
        return documentService.getDocumentsByUserId(userId);
    }

    @QueryMapping
    public Document documentRead(@Argument Long documentId, @Argument Long userId) {

        Document document = documentService.load(documentId);
        if (Objects.equals(document.getUser().getId(), userId)) return document;
        throw new GraphQLException();
    }

    @MutationMapping
    public boolean documentEditBody(@Argument Long documentId, @Argument String body) {
        Document document = documentService.load(documentId);
        if (Objects.equals(document.getBody(), body)) return true;
        document.setBody(body);
        documentService.update(document);
        return true;
    }

    @MutationMapping
    public boolean documentDelete(@Argument Long documentId) {
        documentService.delete(documentId);
        return true;
    }

    @MutationMapping
    public boolean documentRename(@Argument Long documentId, @Argument String title) {
        Document document = documentService.load(documentId);
        if (Objects.equals(document.getTitle(), title)) return true;
        document.setTitle(title);
        documentService.update(document);
        return true;
    }

    @MutationMapping
    public boolean documentCreate(@Argument Long userId, @Argument String title, @Argument Long courseId, @Argument Long subjectId) {

        User user = userService.load(userId);
        Document newDocument = new Document();
        newDocument.setTitle(title);
        newDocument.setUser(user);

        if (courseId != null && courseId != 0) {
            newDocument.setCourse(courseService.load(courseId));
            if (subjectId != null && subjectId != 0) newDocument.setSubject(subjectService.load(subjectId));
        }

        documentService.create(newDocument);

        return true;

    }

}
