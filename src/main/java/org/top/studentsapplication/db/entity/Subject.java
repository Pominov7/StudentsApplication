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

    @OneToMany(mappedBy = "subject", cascade = {CascadeType.PERSIST})
    private Set<Student> students;

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
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

//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }

    @Override
    public String toString() {
        return String.format("%-4s %-15s",
                id, subjectName);
    }
}
