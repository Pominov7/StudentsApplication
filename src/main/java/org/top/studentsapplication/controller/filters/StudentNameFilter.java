package org.top.studentsapplication.controller.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.top.studentsapplication.db.entity.Student;
import org.top.studentsapplication.service.StudentService;

import java.util.List;

// фильтр для имени студента
public class StudentNameFilter {
    @Autowired
    private StudentService studentService;
    private String match; // строка фильтра

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public List<Student> getFilteredStudents() {
        //фильтрация студентов на основе включения match в имя и фамилию
        return studentService.findByContains(match);
    }
}
