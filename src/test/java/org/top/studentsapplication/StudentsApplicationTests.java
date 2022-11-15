package org.top.studentsapplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.top.studentsapplication.db.entity.Student;
import org.top.studentsapplication.db.repository.StudentsRepository;

@SpringBootTest
class StudentsApplicationTests {

    @Autowired
    private StudentsRepository repository;

    @Test
    public void testCreateStudent() {
        Student newStudent = new Student();
        newStudent.setLastName("Test3");
        newStudent.setFirstName("Test3");
        Student saved = repository.save(newStudent);
        System.out.println(saved);
    }

}
