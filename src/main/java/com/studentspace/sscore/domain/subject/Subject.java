    package com.studentspace.sscore.domain.subject;

    import com.studentspace.sscore.domain.academic_course.AcademicCourse;
    import com.studentspace.sscore.domain.user.User;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    @Getter
    @Setter
    @Entity
    @Table(name = "subject", schema = "public")
    public class Subject {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private Long id;

        @Column(name = "name")
        private String name;

        @Column(name = "color")
        private String color;

        @ManyToOne
        @JoinColumn(name = "academic_course_id")
        private AcademicCourse academicCourse;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
    }
