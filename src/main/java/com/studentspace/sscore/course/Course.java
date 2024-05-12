    package com.studentspace.sscore.course;

    import com.studentspace.sscore.user.User;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    @Getter
    @Setter
    @Entity
    @Table(name = "course", schema = "public")
    public class Course {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private Long id;

        @Column
        private String name;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
    }
