package org.top.studentsapplication.controller.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.top.studentsapplication.db.entity.Student;
import org.top.studentsapplication.service.StudentService;

import java.util.List;

// фильтр для имени студента
@Service
public class StudentNameFilter {

    private String match = "";   // строка фильтра
    public String getMatch() {
        return match;
    }
    public void setMatch(String match) {
        this.match = match;
    }

    public List<Student> getFilteredStudents(StudentService service) {
        // фильтрация студентов на основе включения match в имя или фамилию
        return service.findByContains(match);
    }
}