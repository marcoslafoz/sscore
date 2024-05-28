package com.studentspace.sscore.domain.document;

import com.studentspace.sscore.security.JwtService;
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
    private JwtService jwtService;

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

    @QueryMapping
    public Document getDocument(@Argument Long documentId, @Argument Long userId){

        Document document = documentService.load(documentId);
        if(Objects.equals(document.getUser().getId(), userId)) return document;
        throw new GraphQLException();
    }

    @MutationMapping
    public boolean editDocumentBody(@Argument Long documentId, @Argument String body){

        Document document = documentService.load(documentId);

        if(Objects.equals(document.getBody(), body)) return true;

        document.setBody(body);
        documentService.update(document);
        return true;
    }

}
