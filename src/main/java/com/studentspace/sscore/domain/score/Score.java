    package com.studentspace.sscore.domain.score;

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
    @Table(name = "score", schema = "public")
    public class Score {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "name")
        private String name;

        @Column(name = "score")
        private Float score;

        @Column(name = "date")
        private ZonedDateTime date;

        @Column(name = "status")
        private Integer status;

        @ManyToOne
        @JoinColumn(name="course_id")
        private Course course;

        @ManyToOne
        @JoinColumn(name="subject_id")
        private Subject subject;


        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
    }
