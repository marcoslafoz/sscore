package com.studentspace.sscore.domain.event;

import com.studentspace.sscore.domain.course.CourseService;
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

@Controller
@RestController
@Transactional
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @QueryMapping
    public List<Event> eventGetListByUser(@Argument Long userId) {
        return eventService.getEventsByUserId(userId);
    }







    @MutationMapping
    public boolean eventAdd(@Argument Long userId, @Argument Event event) {

        User user = userService.load(userId);

        if (user == null) return false;

        Event newEvent = new Event();

        newEvent.setTitle(event.getTitle());
        newEvent.setAllDay(event.getAllDay());

        newEvent.setUser(user);

        if(event.getColor() != null) newEvent.setColor(event.getColor());
        if (event.getDescription() != null) newEvent.setDescription(event.getDescription());

        if (event.getStart() != null) {
            try {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(event.getStart().toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                newEvent.setStart(zonedDateTime);
            } catch (DateTimeParseException e) {
                return false;
            }
        }

        if (event.getEnd() != null) {
            try {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(event.getEnd().toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                newEvent.setEnd(zonedDateTime);
            } catch (DateTimeParseException e) {
                return false;
            }
        }

//        if (task.getCourse().getId() != null) newTask.setCourse(courseService.load(task.getCourse().getId()));

        eventService.create(newEvent);

        return true;
    }

    @MutationMapping
    public boolean eventChange(@Argument Long userId, @Argument Event event) {

        Event changeEvent = eventService.load(event.getId());
        changeEvent.setAllDay(event.getAllDay());

        if (event.getStart() != null) {
            try {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(event.getStart().toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                changeEvent.setStart(zonedDateTime);
            } catch (DateTimeParseException e) {
                return false;
            }
        }

        if (event.getEnd() != null) {
            try {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(event.getEnd().toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                changeEvent.setEnd(zonedDateTime);
            } catch (DateTimeParseException e) {
                return false;
            }
        }

        eventService.create(changeEvent);

        return true;
    }





}
