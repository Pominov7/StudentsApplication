package org.top.studentsapplication.db.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "group_t")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String groupName;

    @OneToMany(mappedBy = "group", cascade = {CascadeType.PERSIST})
    private Set<Student> students;

    @PreRemove
    private void preRemove(){
        students.forEach(student -> student.setGroup(null));
    }
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%-4s %-15s", id, groupName);
    }
}
