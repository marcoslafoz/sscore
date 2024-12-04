    package com.studentspace.sscore.domain.task;

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
    @Table(name = "task", schema = "public")
    public class Task {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "title")
        private String title;

        @Column(name = "description")
        private String description;

        @Column(name = "checked")
        private Boolean checked;

        @Column(name = "date")
        private ZonedDateTime date;

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
