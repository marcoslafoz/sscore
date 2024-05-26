package com.studentspace.sscore.domain.document;

import com.studentspace.sscore.domain.subject.Subject;
import com.studentspace.sscore.domain.subject.SubjectService;
import com.studentspace.sscore.domain.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@Transactional
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @QueryMapping
    public List<Document> getDocumentListByUserId(@Argument Long userId) {
        return documentService.getDocumentsByUserId(userId);
    }

    @QueryMapping
    public List<Document> getDocumentsBySubjectId(@Argument Long subjectId) {
        return documentService.getDocumentsBySubjectId(subjectId);
    }

    @QueryMapping
    public List<Document> getDocumentsByAcademicCourseId(@Argument Long academicCourseId) {
        return documentService.getDocumentsByAcademicCourseId(academicCourseId);
    }

}
