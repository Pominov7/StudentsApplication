package org.top.studentsapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.top.studentsapplication.db.entity.Subject;
import org.top.studentsapplication.service.SubjectService;

import java.util.List;

@Controller
@RequestMapping(value = "/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // READ (получить все предметы)
    @GetMapping
    public String showAllSubjects(Model model) {
        List<Subject> listSubjects = subjectService.listAllSubjects();
        model.addAttribute("listSubjects", listSubjects);
        return "subjects-list";
    }
}
