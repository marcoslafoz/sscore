    package com.studentspace.sscore.domain.event;

    import com.studentspace.sscore.domain.course.Course;
    import com.studentspace.sscore.domain.subject.Subject;
    import com.studentspace.sscore.domain.user.User;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.time.ZonedDateTime;

    @Getter
    @Setter
    @Entity
    @Table(name = "event", schema = "public")
    public class Event {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "title")
        private String title;

        @Column(name = "description")
        private String description;

        @Column(name = "color")
        private String color;

        @Column(name = "all_day")
        private Boolean allDay;

        @Column(name = "start_date")
        private ZonedDateTime start;

        @Column(name = "end_date")
        private ZonedDateTime end;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne
        @JoinColumn(name="course_id")
        private Course course;

        @ManyToOne
        @JoinColumn(name="subject_id")
        private Subject subject;
    }
