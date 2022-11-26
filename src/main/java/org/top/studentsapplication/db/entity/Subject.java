package org.top.studentsapplication.db.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject_t")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false, length = 50)
    private String subjectName;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.PERSIST)
    private Set<Mark> assessments;

    public Set<Mark> getAssessments() {
        return assessments;
    }

    public void setAssessments(Set<Mark> assessments) {
        this.assessments = assessments;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%-15s",
                subjectName);
    }
}
