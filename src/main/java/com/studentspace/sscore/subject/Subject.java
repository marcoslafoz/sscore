    package com.studentspace.sscore.subject;

    import com.studentspace.sscore.academic_course.AcademicCourse;
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

        @ManyToOne
        @JoinColumn(name = "academic_course_id")
        private AcademicCourse academicCourse;
    }
