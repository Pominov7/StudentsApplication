package org.top.studentsapplication.db.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "student_t")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String firstName;

    @Column(nullable = false, length = 200)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = true)
    private Group group;


    public Group getGroup() {
        return group;
    }


    public void setGroup(Group group) {
        this.group = group;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("%-4s %-18s %-16s",
                id, firstName, lastName);
    }
}
