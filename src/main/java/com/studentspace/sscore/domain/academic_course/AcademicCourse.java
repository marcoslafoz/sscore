    package com.studentspace.sscore.domain.academic_course;

    import com.studentspace.sscore.domain.user.User;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    @Getter
    @Setter
    @Entity
    @Table(name = "academic_course", schema = "public")
    public class AcademicCourse {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private Long id;

        @Column
        private String name;

        @Column(name = "color")
        private String color;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
    }
