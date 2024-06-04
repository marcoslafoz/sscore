package com.studentspace.sscore.domain.score;

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

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
@RestController
@Transactional
public class ScoreController {

    @Autowired ScoreService scoreService;

    @QueryMapping
    public List<Score> scoreGetListBySubject (@Argument Long subjectId){
        return scoreService.getScoresBySubjectId(subjectId);
    }

    @QueryMapping
    public List<Score> scoreGetListByUser (@Argument Long userId){
        return scoreService.getScoresByUserId(userId);
    }

}
