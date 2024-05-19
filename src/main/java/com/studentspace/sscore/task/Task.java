    package com.studentspace.sscore.task;

    import com.studentspace.sscore.academic_course.AcademicCourse;
    import com.studentspace.sscore.subject.Subject;
    import com.studentspace.sscore.user.User;
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
        @JoinColumn(name = "subject_id")
        private Subject subject;

        @ManyToOne
        @JoinColumn(name="academic_course_id")
        private AcademicCourse academicCourse;
    }
