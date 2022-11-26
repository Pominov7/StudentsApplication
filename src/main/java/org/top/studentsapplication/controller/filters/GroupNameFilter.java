package org.top.studentsapplication.controller.filters;

import org.springframework.stereotype.Service;
import org.top.studentsapplication.db.entity.Group;
import org.top.studentsapplication.db.entity.Student;
import org.top.studentsapplication.service.GroupService;
import org.top.studentsapplication.service.StudentService;

import java.util.List;

// фильтр для названия группы
@Service
public class GroupNameFilter {
    private String match = "";   // строка фильтра

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public List<Group> getFilteredGroups(GroupService service) {
        // фильтрация групп на основе включения match в название группы
        return service.findGroupByContains(match);
    }
}
