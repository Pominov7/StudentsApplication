package org.top.studentsapplication.db.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mark_t")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private Integer assessment;

    @Column(nullable = false, length = 200)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = true)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = true)
    private Subject subject;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssessment() {
        return assessment;
    }

    public void setAssessment(Integer assessment) {
        this.assessment = assessment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return String.format("%-4s %-18s %-16s",
                id, assessment, date);
    }
}
