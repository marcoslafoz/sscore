package com.studentspace.sscore.domain.score;

import com.studentspace.sscore.domain.course.Course;
import com.studentspace.sscore.domain.course.CourseService;
import com.studentspace.sscore.domain.document.Document;
import com.studentspace.sscore.domain.subject.Subject;
import com.studentspace.sscore.domain.subject.SubjectService;
import com.studentspace.sscore.domain.task.Task;
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
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    SubjectService subjectService;

    @QueryMapping
    public List<Score> scoreGetListBySubject (@Argument Long subjectId){
        return scoreService.getScoresBySubjectId(subjectId);
    }

    @QueryMapping
    public List<Score> scoreGetListByUser (@Argument Long userId){
        return scoreService.getScoresByUserId(userId);
    }

    @MutationMapping
    public boolean scoreDelete (@Argument Long scoreId){
        scoreService.delete(scoreId);
        return true;
    }

    @MutationMapping
    public boolean scoreAdd(@Argument Long userId, @Argument Score score) {

        User user = userService.load(userId);

        if (user == null) return false;

        Score newScore = new Score();

        newScore.setName(score.getName());
        newScore.setScore(score.getScore());
        newScore.setStatus(score.getStatus());
        newScore.setUser(user);

        if (score.getDate() != null) {
            try {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(score.getDate().toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                newScore.setDate(zonedDateTime);
            } catch (DateTimeParseException e) {
                return false;
            }
        }

        if (score.getCourse().getId() != null && score.getCourse().getId() != 0) {
            Course course = courseService.load(score.getCourse().getId());
            newScore.setCourse(course);

            if (score.getSubject().getId() != null && score.getSubject().getId() != 0) {
                Subject subject = subjectService.load(score.getSubject().getId());
                if (!Objects.equals(subject.getCourse().getId(), course.getId())) {
                    subject = null;
                }
                newScore.setSubject(subject);
            }else{
                newScore.setSubject(null);
            }

        }else{
            newScore.setCourse(null);
            newScore.setSubject(null);
        }

        scoreService.create(newScore);

        return true;
    }

    @MutationMapping
    public boolean scoreEdit(@Argument Score score) {


        Score editedScore = scoreService.load(score.getId());

        editedScore.setName(score.getName());
        editedScore.setScore(score.getScore());
        editedScore.setStatus(score.getStatus());

        if (score.getDate() != null) {
            try {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(score.getDate().toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                editedScore.setDate(zonedDateTime);
            } catch (DateTimeParseException e) {
                return false;
            }
        }else{
            editedScore.setDate(null);
        }

        if (score.getCourse().getId() != null && score.getCourse().getId() != 0) {
            Course course = courseService.load(score.getCourse().getId());
            editedScore.setCourse(course);

            if (score.getSubject().getId() != null && score.getSubject().getId() != 0) {
                Subject subject = subjectService.load(score.getSubject().getId());
                if (!Objects.equals(subject.getCourse().getId(), course.getId())) {
                    subject = null;
                }
                editedScore.setSubject(subject);
            }else{
                editedScore.setSubject(null);
            }

        }else{
            editedScore.setCourse(null);
            editedScore.setSubject(null);
        }

        scoreService.create(editedScore);

        return true;
    }

}
