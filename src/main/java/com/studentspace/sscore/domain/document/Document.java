package com.studentspace.sscore.domain.document;

import com.studentspace.sscore.domain.course.Course;
import com.studentspace.sscore.domain.subject.Subject;
import com.studentspace.sscore.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "document", schema = "public")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private String body;

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
