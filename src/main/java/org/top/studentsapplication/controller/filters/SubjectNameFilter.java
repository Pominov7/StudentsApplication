package org.top.studentsapplication.controller.filters;

import org.springframework.stereotype.Service;
import org.top.studentsapplication.db.entity.Group;
import org.top.studentsapplication.db.entity.Subject;
import org.top.studentsapplication.service.GroupService;
import org.top.studentsapplication.service.SubjectService;

import java.util.List;

// фильтр для названия предмета
@Service
public class SubjectNameFilter {
    private String match = "";   // строка фильтра

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public List<Subject> getFilteredSubject(SubjectService service) {
        // фильтрация групп на основе включения match в название предмета
        return service.findSubjectByContains(match);
    }
}
